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
        //List<String> terminals = Arrays.asList(Data.terminals);
        List<String> nonTerminals = Arrays.asList(Data.nonterminals);
        boolean accepted;
        Stack<Token> stack = new Stack<Token>();
        stack.push(new NonTerminalToken("prog"));
        accepted = false;
        while (!accepted)
        {
            if (isTerminal(stack.peek()))
            {
                if(match(ts.get(tsIndex),stack.peek()))
                {
                    tsIndex++;
                }
                if (stack.peek().getClass().getSimpleName() == "endOfFileToken")
                {
                    accepted = true;
                }
                stack.pop();
            }
            else
            {
                int p = Data.generateTable().get();

            }
        }

    }

    boolean isTerminal(Token t)
    {
        return true;
    }

    boolean match(Token a, Token b) throws Exception
    {
        if (a == b)
        {
            return true;
        }
        throw new Exception("Unexpected Token");
    }
}
