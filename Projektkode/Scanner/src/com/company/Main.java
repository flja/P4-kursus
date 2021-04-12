package com.company;
import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Scanner1 scanner = new Scanner1();
        Parser parser = new Parser();
        Node node = parser.LLparser(scanner.Lexer());
        System.out.println("\n\n");
        printNode(node);
        while (node != null)
        {
            if (node.leftMostChild != null && node.leftMostChild.visited)
            {
                node = node.leftMostChild;
                printNode(node);
            }
            else if (node.rightSib != null && node.rightSib.visited)
            {
                node = node.rightSib;
                printNode(node);
            }
            else if (node.parent != null)
            {
                node = node.parent;
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
        //Scanner1 s = new Scanner1();
        //for (Token token : s.Lexer())
        //{
        //    System.out.println(token.getClass().getSimpleName());

	// write your code here
    }
    public static void printNode(Node node)
    {
        if(node instanceof TerminalNode)
        {
            System.out.println(((TerminalNode) node).terminal.getClass().getSimpleName());
        }
        else
        {
            System.out.println(((NonTerminalNode) node).nonterminal);
        }
    }
}


