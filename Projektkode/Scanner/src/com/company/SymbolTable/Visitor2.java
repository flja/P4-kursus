package com.company.SymbolTable;

import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.Parser;
import com.company.Tokens.idToken;

public class Visitor2 extends Visitor
{
    public Visitor2(ScopeTable aTable, Node aAstTree)
    {
        super(aTable, aAstTree);
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
                case "rbrace" :
                    CloseScope();
                case "id":
                    if (!(((NonTerminalNode) (node.parent)).nonterminal.toLowerCase().contains("dcl")))
                    {
                        if (RetrieveSymbol(((idToken) ((TerminalNode) node).terminal).spelling) == null)
                        {
                            throw new Exception("Undeclared symbol: " + ((idToken) ((TerminalNode) node).terminal).spelling);
                        }
                    }
                default :
            }
        }
        else if(node instanceof NonTerminalNode)
        {
            switch (((NonTerminalNode) node).nonterminal)
            {
                case "DeckDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "deck"));
                case "NumberDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "number"));
                case "CardDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "card"));
                case "HandDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "hand"));
                case "EnumDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "enum"));
                case "StringDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "string"));
                case "FlagDcl" :
                    EnterSymbol(
                            new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling),
                                    "flag"));
            }
        }
    }
}
