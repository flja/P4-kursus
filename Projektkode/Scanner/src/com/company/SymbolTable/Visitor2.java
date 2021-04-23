package com.company.SymbolTable;

import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.Parser;
import com.company.Tokens.idToken;

public class Visitor2 extends Visitor
{
    public Visitor2(ScopeTable aTable, Node aAstTree) throws  Exception
    {
        super(aTable, aAstTree);
        Symbol s = new Symbol("king", "number");
        EnterSymbol(s);
        s = new Symbol("queen", "number");
        EnterSymbol(s);
        s = new Symbol("jack", "number");
        EnterSymbol(s);
        s = new Symbol("ace", "number");
        EnterSymbol(s);
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
                    EnterSymbolHelper(node, "deck");
                    break;
                case "NumberDcl" :
                    EnterSymbolHelper(node, "number");
                    break;
                case "CardDcl" :
                    EnterSymbolHelper(node, "card");
                    break;
                case "HandDcl" :
                    EnterSymbolHelper(node, "hand");
                    break;
                case "EnumDcl" :
                    EnterSymbolHelper(node, "enum");
                    break;
                case "StringDcl" :
                    EnterSymbolHelper(node, "string");
                    break;
                case "FlagDcl" :
                    EnterSymbolHelper(node, "flag");
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

    void EnterSymbolHelper(Node node, String type) throws Exception
    {
        EnterSymbol(
                new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                        type));
        node.visited = true;
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
