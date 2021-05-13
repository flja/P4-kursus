package com.company.CodeGenerator;

import com.company.AST.AST;
import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.CodeGenerator.TemplateCode.DeckClass;
import com.company.CodeGenerator.TemplateCode.HelpMethods;
import com.company.Parser;
import com.company.ShufflerSymbols.CardsClass;
import com.company.Tokens.cardValueToken;
import com.company.Tokens.idToken;
import com.company.Tokens.nonZeroNumToken;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JavaGenerator {
    String path = Paths.get(".").toAbsolutePath().normalize().toString() + "/Javafile.java";
    String code = "";

    public JavaGenerator() {

    }

    public void generateTemplate() {
        code = "package shufflerCode;\n" +
                "import com.company.CodeGenerator.TemplateCode;\n" +
                "public class Main \n    {\n" +
                "    public static void main(String[] args)\n{\n" +
                "}\n";
    }

    /*public void main() throws Exception {
        //setup
        Cards cards = new Cards();
        List<Player> players = new Player().GeneratePlayers;
        Table table = new Table();
        Setup setup = new Setup();
        Round round = new Round();
        Turn turn = new Turn();
        Endcondition endcondition = new Endcondition();
        //flow
        cards.run();
        setup.run();
        while (!endcondition.end) {
            round.run();
        }


        DeckClass gamedeck = new DeckClass(new String[]{"Standard"});
    }*/

    public void WriteToFile() throws Exception {
        FileWriter fw = new FileWriter(path);
        fw.write(code);
        fw.flush();
        fw.close();
    }

    void Emit(String input)
    {
        code += input;
    }

    public String DeckGenerator(Node node)
    {
        String s = "{";
        s = DeckGeneratorRecursion(node, s);
        s = s.substring(0,s.length() - 2);
        s = s +"}";
        return "new DeckClass(new String[]" + s +")";
    }
    public String DeckGeneratorRecursion(Node node, String s)
    {
        if (node instanceof TerminalNode)
        {
            String cards = Parser.GetName(((TerminalNode) node).terminal);
            switch(cards)
            {
                case "cardvalue":
                    cardValueToken cardvalueToken = (cardValueToken) ((TerminalNode) node).terminal;
                    s += Character.toString(cardvalueToken.facevalue) + Character.toString(cardvalueToken.suit) + ", ";
                    break;
                case "plus":
                    break;
                default:
                    s += cards + ", ";
                    break;
            }
        }
        node.visited = true;
        for (Node child: node.GetChildren()) {
            s = DeckGeneratorRecursion(child, s);
        }
        return s;
    }

    public String CardDefGenerator(Node node) throws Exception {
        String CardDefBlock =  "public class Cards\n" +
                                "{\n" +
                                "public Cards()\n" +
                                "{\n";
        Generator gen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.rightSib));
        gen.RecursiveVisitor(gen._ast.Root);
        CardDefBlock += gen.javagenerator.code;
        CardDefBlock += "}\n}\n";
        node.VisitSuptree();
        return CardDefBlock;
    }
    public String PlayerDefGenerator(Node node) throws Exception {
        String PlayerDefBlock =  "public class Player\n" +
                "{\n";
        Generator DclGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.rightSib.rightSib.rightSib.leftMostChild.rightSib)); //PlayerDef -> CompoundStmt -> Dcls
        DclGen.RecursiveVisitor(DclGen._ast.Root);
        PlayerDefBlock += DclGen.javagenerator.code;
        PlayerDefBlock += "public Player()\n" +
                "{\n";
        Generator StmtGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.rightSib.rightSib.rightSib.leftMostChild.rightSib.rightSib));//PlayerDef -> CompoundStmt -> Stmts
        StmtGen.RecursiveVisitor(StmtGen._ast.Root);
        PlayerDefBlock += StmtGen.javagenerator.code;
        PlayerDefBlock += "}\n}\n";
        PlayerDefBlock += "int PlayerCnt = " + ((nonZeroNumToken) ((TerminalNode) node.leftMostChild.rightSib.rightSib.rightSib).terminal).value + ";\n";
        node.VisitSuptree();

        return PlayerDefBlock;
    }

    public String HandDclGenerator(Node node) throws Exception
    {
        node.VisitSuptree();
        return "hand " + ((idToken) ((TerminalNode) node.leftMostChild.rightSib).terminal).spelling + " = new hand()";
    }
    public String DeckDclGenerator(Node node) throws Exception
    {
        node.VisitSuptree();
        return "DeckClass " + ((idToken) ((TerminalNode) node.leftMostChild.rightSib).terminal).spelling + " = new DeckClass()";
    }

    public String TableDefGenerator(Node node) throws Exception {
        String TableDefBlock =  "public class Table\n" +
                "{\n";
        Generator DclGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.leftMostChild.rightSib)); //TableDef -> CompoundStmt -> Dcls
        DclGen.RecursiveVisitor(DclGen._ast.Root);
        TableDefBlock += DclGen.javagenerator.code;
        TableDefBlock += "public Table()\n" +
                "{\n";
        Generator StmtGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.leftMostChild.rightSib.rightSib));//TableDef -> CompoundStmt -> Stmts
        StmtGen.RecursiveVisitor(StmtGen._ast.Root);
        TableDefBlock += StmtGen.javagenerator.code;
        TableDefBlock += "}\n}\n";
        node.VisitSuptree();
        return TableDefBlock;
    }

}
