package com.company;
import com.company.AST.AST;
import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.ShufflerSymbols.LoadShufflerSymbols;
import com.company.ShufflerSymbols.ShufflerSymbols;
import com.company.SymbolTable.ScopeTable;
import com.company.SymbolTable.SymbolTableGenerator;

import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception
    {
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        ShufflerSymbols shufflerSymbols = new LoadShufflerSymbols().Load();
        Scanner1 scanner = new Scanner1();
        Parser parser = new Parser();
        AST ast = parser.LLparser(scanner.Lexer());
        Node node = ast.Root;
        prettyPrintAST(node);
        SymbolTableGenerator symbolTableGenerator = new SymbolTableGenerator(ast);
        ScopeTable SymbolTable = symbolTableGenerator.GenerateSymbolTable();
        PrintSymbolTable(SymbolTable);
    }
    public static void printNode(Node node, int indents)
    {
        String indentation = "";
        for (int i = 0; i < indents; i++)
        {
            indentation += "|   ";
        }
        if(node instanceof TerminalNode)
        {
            System.out.println(indentation  + ((TerminalNode) node).terminal.getClass().getSimpleName());
        }
        else
        {
            System.out.println(indentation + ((NonTerminalNode) node).nonterminal);
        }
    }

    public static void prettyPrintAST(Node node)
    {
        int indent = 0;
        System.out.println("\n\n");
        printNode(node, indent);
        while (node != null)
        {
            if (node.leftMostChild != null && node.leftMostChild.visited)
            {
                node = node.leftMostChild;
                indent += 1;
                printNode(node, indent);
            }
            else if (node.rightSib != null && node.rightSib.visited)
            {
                node = node.rightSib;
                printNode(node, indent);
            }
            else if (node.parent != null)
            {
                node = node.parent;
                indent -= 1;
            }
            else
            {
                node = null;
            }
            if (node != null)
            {
                node.visited = false;
            }
        }
    }

    public static void PrintSymbolTable(ScopeTable globalScope)
    {
        int indent = 1;
        System.out.println("\n\n");
        System.out.println("SymbolTable:");
        printScopes(globalScope, indent);
    }
    static int j = 0;
    public static void printScopes(ScopeTable scopeTable, int indent)
    {
        if (scopeTable.table.keySet().size() <= 0)
        {
            //System.out
        }
        System.out.println("Scope " + j);
        for (String item : scopeTable.table.keySet())
        {
            PrintSymbol(scopeTable.table.get(item).ToString(), indent);
        }
        j++;
        indent++;
        for (ScopeTable item : scopeTable.subScopes)
        {
            printScopes(item, indent);
        }
    }

    public static void PrintSymbol(String symbol, int indents)
    {
        String indentation = "";
        for (int i = 0; i < indents; i++)
        {
            indentation += "|   ";
        }
        System.out.println(indentation + symbol.toString());
    }
}


