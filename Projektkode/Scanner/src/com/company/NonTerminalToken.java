package com.company;

public class NonTerminalToken extends Token
{
    public String nonTerminal;
    public NonTerminalToken(int line, String aNonTerminal)
    {
        super(line);
        nonTerminal = aNonTerminal;
    }
}
