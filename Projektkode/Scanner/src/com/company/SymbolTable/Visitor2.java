package com.company.SymbolTable;

import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.Tokens.nonZeroNumToken;

public class Visitor2 extends Visitor
{
    int playerCnt;
    int state  = 0;
    boolean done = false;
    public Visitor2(ScopeTable aTable, Node aAstTree)
    {
        super(aTable, aAstTree);
    }

    @Override
    void RecursiveVisitor(Node node) throws Exception {
        if (((NonTerminalNode) node).nonterminal == "Setup")
        {
            done = true;
        }
        if (!done)
        {
            Visit(node);
            for (Node child : node.GetChildren())
            {
                RecursiveVisitor(child);
            }
        }
    }
    @Override
    void Visit(Node node) throws Exception
    {
        if (node instanceof NonTerminalNode)
        {
            if (((NonTerminalNode) node).nonterminal == "PlayerDef")
            {
                playerCnt = ((nonZeroNumToken) ((TerminalNode) node.leftMostChild.rightSib.rightSib.rightSib).terminal).value;
            }
            if (((NonTerminalNode) node).nonterminal == "TableDef")
            {
                state = 1;
            }
        }
        switch (state)
        {
            case 0:
                PlayerScope(node);
                break;
            case 1:
                TableScope(node);
                break;

        }


    }
    void PlayerScope(Node node) throws Exception
    {
        if (node instanceof NonTerminalNode)
        {
            {
                switch (((NonTerminalNode) node).nonterminal)
                {
                    case "DeckDcl" :
                        EnterPlayerSymbol(EnterDclSymbolHelper(node, "deck"));
                        break;
                    case "NumberDcl" :
                        EnterPlayerSymbol(EnterDclSymbolHelper(node, "number"));
                        break;
                    case "CardDcl" :
                        EnterPlayerSymbol(EnterDclSymbolHelper(node, "card"));
                        break;
                    case "HandDcl" :
                        EnterPlayerSymbol(EnterDclSymbolHelper(node, "hand"));
                        break;
                    case "EnumDcl" :
                        EnterPlayerSymbol(EnterDclSymbolHelper(node, "enum"));
                        break;
                    case "StringDcl" :
                        EnterPlayerSymbol(EnterDclSymbolHelper(node, "string"));
                        break;
                    case "FlagDcl" :
                        EnterPlayerSymbol(EnterDclSymbolHelper(node, "flag"));
                        break;
                }
            }
        }
    }
    void EnterPlayerSymbol(Symbol symbol) throws Exception
    {
        for (int i = 1; i <= playerCnt; i++)
        {
            EnterGlobalSymbol(new Symbol("player." + i + "." + symbol.Id(), symbol.Type()));
        }
    }

    void TableScope(Node node) throws Exception
    {
        Symbol symbol = null;
        if (node instanceof NonTerminalNode)
        {
            {
                switch (((NonTerminalNode) node).nonterminal)
                {
                    case "DeckDcl" :
                        symbol = EnterDclSymbolHelper(node, "deck");
                        break;
                    case "NumberDcl" :
                        symbol = EnterDclSymbolHelper(node, "number");
                        break;
                    case "CardDcl" :
                        symbol = EnterDclSymbolHelper(node, "card");
                        break;
                    case "HandDcl" :
                        symbol = EnterDclSymbolHelper(node, "hand");
                        break;
                    case "EnumDcl" :
                        symbol = EnterDclSymbolHelper(node, "enum");
                        break;
                    case "StringDcl" :
                        symbol = EnterDclSymbolHelper(node, "string");
                        break;
                    case "FlagDcl" :
                        symbol = EnterDclSymbolHelper(node, "flag");
                        break;
                }
                if (symbol != null)
                {
                    EnterGlobalSymbol(new Symbol("table."+ symbol.Id(), symbol.Type()));
                }
            }
        }
    }
}