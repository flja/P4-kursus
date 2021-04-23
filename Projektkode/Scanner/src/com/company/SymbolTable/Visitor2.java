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
                    if (!(((NonTerminalNode) (node.parent)).nonterminal.toLowerCase().contains("dcl")))
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
                    EnterSymbol(
                            new Symbol((((idToken) (((TerminalNode)node.leftMostChild.rightSib)).terminal).spelling),
                                    "deck"));
                    break;
                case "NumberDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "number"));
                    break;
                case "CardDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "card"));
                    break;
                case "HandDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "hand"));
                    break;
                case "EnumDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "enum"));
                    break;
                case "StringDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "string"));
                    break;
                case "FlagDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "flag"));
                    break;
            }
        }
    }
}
