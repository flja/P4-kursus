package com.company;
import com.company.AST.AST;
import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.SymbolTable.SymbolTableGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Scanner1 scanner = new Scanner1();
        Parser parser = new Parser();
        AST ast = parser.LLparser(scanner.Lexer());
        Node node = ast.Root;
        prettyPrintTree(node);
        SymbolTableGenerator symbolTableGenerator = new SymbolTableGenerator(ast);
        
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

    public static void prettyPrintTree(Node node)
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
}


