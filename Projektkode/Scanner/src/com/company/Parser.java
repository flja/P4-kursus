package com.company;
import javax.swing.*;
import java.nio.file.FileSystems;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser
{
    public void LLparser(List<Token> ts) throws Exception
    {

        int tsIndex = 0;
        List<String> terminals = Arrays.asList(Data.terminals);
        List<String> nonTerminals = Arrays.asList(Data.nonterminals);
        boolean accepted;
        Stack<String> stack = new Stack<String>();
        stack.push("eof");
        stack.push("Prog");
        accepted = false;
        while (!accepted)
        {
            System.out.println("stack: " + stack.peek() + " ts: " + GetName(ts.get(tsIndex)));
            System.out.println("Line: " + ts.get(tsIndex).line);
            if (isTerminal(stack.peek()))
            {
                System.out.println("is terminal");
                if(match(ts.get(tsIndex),stack.peek()))
                {
                    System.out.println("matches");
                    tsIndex++;
                }
                if (stack.peek() == "eof")
                {
                    System.out.println("eof");
                    accepted = true;
                }
                stack.pop();
            }
            else
            {
                System.out.println("is nonterminal");
                int p = Data.generateTable().get(nonTerminals.indexOf(stack.peek())).get(terminals.indexOf(GetName(ts.get(tsIndex))));
                System.out.println(nonTerminals.indexOf(stack.peek()) + " " + terminals.indexOf(GetName(ts.get(tsIndex))));
                if (p == 0)
                {
                    throw new Exception("P = 0 " + ts.get(tsIndex).line);
                }
                System.out.println("Production: " + p);
                List<String> A = Data.getProduction(p);
                stack.pop();
                for (int i = A.size() - 1; i >= 0; i--)
                {
                    System.out.println(A.get(i) + " Pushed to stack");
                    stack.push(A.get(i));
                }
            }
            System.out.println("\n\n");
        }

    }

    boolean isTerminal(String a)
    {
        return Character.isLowerCase(a.charAt(0));
    }

    boolean match(Token a, String b) throws Exception
    {
        if (GetName(a).equals(b.toLowerCase()))
        {
            return true;
        }
        else
        {
            String message = "Expected " + b + " Token at line " + a.line + " But found a " + GetName(a) + " token";
            if (a instanceof idToken){
                message += " " + ((idToken) a).spelling;
            }
            throw new Exception(message);
        }
    }

    String GetName(Token t)
    {
        return t.getClass().getSimpleName().replaceAll("Token","").toLowerCase();
    }
}
