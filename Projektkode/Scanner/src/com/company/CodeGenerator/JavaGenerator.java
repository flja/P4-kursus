package com.company.CodeGenerator;

import com.company.AST.AST;
import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.CodeGenerator.TemplateCode.ActionClass;
import com.company.CodeGenerator.TemplateCode.DeckClass;
import com.company.CodeGenerator.TemplateCode.HelpMethods;
import com.company.Parser;
import com.company.ShufflerSymbols.CardsClass;
import com.company.Tokens.*;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public String SetupGenerator(Node node) throws Exception
    {
        String SetupBlock =  "public class Setup\n" +
                "{\n";
        Generator DclGen = new Generator(new AST(node.leftMostChild.rightSib.leftMostChild.rightSib)); //Setup -> CompoundStmt -> Dcls
        DclGen.RecursiveVisitor(DclGen._ast.Root);
        SetupBlock += DclGen.javagenerator.code;
        SetupBlock += "public void run()\n" +
                "{\n";
        Generator StmtGen = new Generator(new AST(node.leftMostChild.rightSib.leftMostChild.rightSib.rightSib));//Setup -> CompoundStmt -> Stmts
        StmtGen.RecursiveVisitor(StmtGen._ast.Root);
        SetupBlock += StmtGen.javagenerator.code;
        SetupBlock += "}\n}\n";
        node.VisitSuptree();
        return SetupBlock;
    }
    public String RoundGenerator(Node node) throws Exception
    {
        String RoundBlock =  "public class Round\n" +
                "{\n";
        Generator DclGen = new Generator(new AST(node.leftMostChild.rightSib.leftMostChild.rightSib)); //Round -> CompoundStmt -> Dcls
        DclGen.RecursiveVisitor(DclGen._ast.Root);
        RoundBlock += DclGen.javagenerator.code;
        RoundBlock += "public void run()\n" +
                "{\n";
        Generator StmtGen = new Generator(new AST(node.leftMostChild.rightSib.leftMostChild.rightSib.rightSib));//Round -> CompoundStmt -> Stmts
        StmtGen.RecursiveVisitor(StmtGen._ast.Root);
        RoundBlock += StmtGen.javagenerator.code;
        RoundBlock += "}\n}\n";
        node.VisitSuptree();
        return RoundBlock;
    }
    public String TurnGenerator(Node node) throws Exception
    {
        String TurnBlock =  "public class Turn\n" +
                "{\n";
        Generator DclGen = new Generator(new AST(node.leftMostChild.rightSib.leftMostChild.rightSib)); //Turn -> CompoundStmt -> Dcls
        DclGen.RecursiveVisitor(DclGen._ast.Root);
        TurnBlock += DclGen.javagenerator.code;
        TurnBlock += "public void run()\n" +
                "{\n";
        Generator StmtGen = new Generator(new AST(node.leftMostChild.rightSib.leftMostChild.rightSib.rightSib));//Turn -> CompoundStmt -> Stmts
        StmtGen.RecursiveVisitor(StmtGen._ast.Root);
        TurnBlock += StmtGen.javagenerator.code;
        TurnBlock += "}\n}\n";
        node.VisitSuptree();
        return TurnBlock;
    }

    public String SelectionStatementGenerator(Node node) throws Exception
    {
        switch (Parser.GetName(((TerminalNode) node.leftMostChild).terminal))
        {
            case "if":
                return IfStatementGenerator(node);
            case "actions":
               return ActionsStatementGenerator(node);
            case "switch":
                return SwitchStatementGenerator(node);
        }
        return null;
    }
    public String IfStatementGenerator(Node node) throws Exception
    {
        String s = "if(";
        Generator logicalGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib)); //SelectionStmt -> LogicalExpr
        logicalGen.RecursiveVisitor(logicalGen._ast.Root);
        s += logicalGen.javagenerator.code;
        s += ")";
        Generator StmtGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.rightSib.rightSib)); //SelectionStmt -> Stmt
        StmtGen.RecursiveVisitor(StmtGen._ast.Root);
        s += StmtGen.javagenerator.code;
        s += OptElseGenerator(node.leftMostChild.rightSib.rightSib.rightSib.rightSib.rightSib);//SelectionStmt -> OptElse
        node.VisitSuptree();
        return s;
    }
    public String OptElseGenerator(Node node) throws Exception {
        String s = "";
        if (((TerminalNode) node.leftMostChild).terminal instanceof elseToken)
        {
            s = "else\n";
            Generator stmtGen = new Generator(new AST(node.leftMostChild.rightSib)); //OptElse -> Stmt
            stmtGen.RecursiveVisitor(stmtGen._ast.Root);
            s += stmtGen.javagenerator.code;
        }
        return s;
    }
    public String ActionsStatementGenerator(Node node) throws Exception
    {
        List<ActionClass> actions = new ArrayList<ActionClass>();
        actions = FindActions(node.leftMostChild.rightSib, actions);
        String s = "int _ActionCnt = 1;\n";
        s += "int[] _ActionMapping = new int[" + actions.size() + "];\n";

        for (ActionClass item: actions) {
            s += "if(" + item.logicalExpr + ")" +
                    "\n{System.out.println(_ActionCnt" + " + \": \" + " + item.name + ");\n" +
                    "_ActionMapping[" + actions.indexOf(item) + "] = _ActionCnt;\n" +
                    "_ActionCnt++;\n}\n";
        }

       s += "System.out.println(\"Choose an action to perform: \");" +
        "Scanner _ActionScanner = new Scanner(System.in);\n" +
        "int _ActionInput = _ActionScanner.nextInt();\n" +
        "switch (Arrays.asList(_ActionMapping).indexOf(_ActionInput))\n{\n";
        for(ActionClass item : actions)
        {
            s+= "case " + actions.indexOf(item) + ":" +
                    item.body +
                    "break;\n";
        }
        node.VisitSuptree();
        return s;

    }
    public List<ActionClass> FindActions(Node node, List<ActionClass> list) throws Exception {
        if (node instanceof NonTerminalNode)
        {
            if (((NonTerminalNode) node).nonterminal.equals("LabeledStmt"))
            {
                if (((TerminalNode) node.leftMostChild).terminal instanceof actionToken)
                {
                    String name = ((stringValueToken) ((TerminalNode) node.leftMostChild.rightSib.leftMostChild).terminal).value; // LabeledStmt -> String -> stringValue
                    Generator logicGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.rightSib.rightSib)); // LabeledStmt -> LogicalExpr
                    logicGen.RecursiveVisitor(logicGen._ast.Root);
                    String logicalExpr = logicGen.javagenerator.code;
                    Generator StmtGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.rightSib.rightSib.rightSib.rightSib)); // LabeledStmt -> CompoundStmt
                    StmtGen.RecursiveVisitor(StmtGen._ast.Root);
                    String body = StmtGen.javagenerator.code;
                    list.add(new ActionClass(name, logicalExpr, body));
                    return list;
                }
            }
        }
        for (Node child: node.GetChildren()) {
            list = FindActions(child, list);
        }
        return list;
    }
    public String SwitchStatementGenerator(Node node) throws Exception
    {
        String s = "switch(";
        Generator ExprGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib)); //SelectionStmt -> Expr
        ExprGen.RecursiveVisitor(ExprGen._ast.Root);
        s += ExprGen.javagenerator.code;
        s += ")";
        Generator StmtGen = new Generator(new AST(node.leftMostChild.rightSib.rightSib.rightSib.rightSib)); //SelectionStmt -> CompoundStmt
        StmtGen.RecursiveVisitor(StmtGen._ast.Root);
        s += StmtGen.javagenerator.code;
        node.VisitSuptree();
        return s;
    }

}
