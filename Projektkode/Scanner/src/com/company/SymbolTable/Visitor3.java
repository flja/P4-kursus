package com.company.SymbolTable;

import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.Parser;
import com.company.ShufflerSymbols.ShufflerSymbols;
import com.company.Tokens.*;

public class Visitor3 extends Visitor
{


    public Visitor3(ScopeTable aTable, Node aAstTree, ShufflerSymbols aShufflerSymbols) throws Exception {
        super(aTable, aAstTree, aShufflerSymbols);
        Symbol s = new Symbol("king", "number");
        EnterSymbolToCurrentScope(s);
        s = new Symbol("queen", "number");
        EnterSymbolToCurrentScope(s);
        s = new Symbol("jack", "number");
        EnterSymbolToCurrentScope(s);
        s = new Symbol("ace", "number");
        EnterSymbolToCurrentScope(s);
    }

    @Override
    void Visit(Node node) throws Exception
    {
        if (node instanceof TerminalNode)
        {
            switch (Parser.GetName(((TerminalNode) node).terminal))
            {
                case "lbrace" :
                    OpenScope();
                    break;
                case "rbrace" :
                    CloseScope();
                    break;
                case "id":
                    if (!node.visited)
                    {
                        if (RetrieveSymbol(((idToken) ((TerminalNode) node).terminal).spelling) == null)
                        {
                            throw new Exception("Undeclared symbol: " + ((idToken) ((TerminalNode) node).terminal).spelling);
                        }
                    }
                    break;
                default :
            }
        }
        else if(node instanceof NonTerminalNode)
        {
            switch (((NonTerminalNode) node).nonterminal)
            {
                case "DeckDcl" :
                    EnterSymbolToCurrentScope(EnterDclSymbolHelper(node, "deck"));
                    break;
                case "NumberDcl" :
                    EnterSymbolToCurrentScope(EnterDclSymbolHelper(node, "number"));
                    break;
                case "CardDcl" :
                    EnterSymbolToCurrentScope(EnterDclSymbolHelper(node, "card"));
                    break;
                case "HandDcl" :
                    EnterSymbolToCurrentScope(EnterDclSymbolHelper(node, "hand"));
                    break;
                case "EnumDcl" :
                    EnterSymbolToCurrentScope(EnterDclSymbolHelper(node, "enum"));
                    break;
                case "StringDcl" :
                    EnterSymbolToCurrentScope(EnterDclSymbolHelper(node, "string"));
                    break;
                case "FlagDcl" :
                    EnterSymbolToCurrentScope(EnterDclSymbolHelper(node, "flag"));
                    break;
                case "ObjectSpecifier" :
                    String s = HandleObjectSpecifier(node);
                    if (RetrieveSymbol(s) == null)
                    {
                        throw new Exception("Undeclared symbol: " + s );
                    }
                    break;
            }
        }
    }
    @Override
    Symbol EnterDclSymbolHelper(Node node, String type) throws Exception
    {
        node.visited = true;
        return super.EnterDclSymbolHelper(node, type);
    }

    String HandleObjectSpecifier(Node node)
    {
        String id = "";
        Node next = null;
        if (node instanceof NonTerminalNode && node.leftMostChild != null)
        {
            switch (((NonTerminalNode) node).nonterminal)
            {
                case "ObjectSpecifier":
                case "FollowObject":
                    next = node.leftMostChild.rightSib;
                    break;
                case "FollowObject1":
                    next = node.rightSib;
                    break;
                case "String":
                    id += "stringValue";
                    break;
                case "Number":
                    if (id == "player.")
                    {
                        if (node.leftMostChild instanceof NonTerminalNode)
                        {
                            if (node.leftMostChild.leftMostChild == null)
                            {
                                id += ((nonZeroNumToken) ((TerminalNode) node.leftMostChild.rightSib).terminal).value;
                            }
                        }
                    }
                    else
                    {
                        id += "numberValue";
                    }
                    break;
                case "Card":
                    id += "cardValue";
                    break;
                default:
                    next = null;
            }
        }
        if (node.leftMostChild instanceof TerminalNode)
        {
            switch (Parser.GetName(((TerminalNode) node.leftMostChild).terminal))
            {
                case "dot":
                    id += ".";
                    break;
                case "id":
                    id += ((idToken) ((TerminalNode) node.leftMostChild).terminal).spelling;
                    node.leftMostChild.visited = true;
                    break;
            }
        }
        if (next != null)
        {
            id += HandleObjectSpecifier(next);
        }
        return id;
    }
}
