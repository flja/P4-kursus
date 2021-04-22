package com.company.SymbolTable;

import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.Parser;
import com.company.Tokens.idToken;

import java.util.Stack;

public class SymbolTableGenerator
{
    Stack<ScopeTable> stack = new Stack<ScopeTable>();
    ScopeTable GlobalScope = new ScopeTable();

    public SymbolTableGenerator()
    {
        stack.push(GlobalScope);
    }

    public void GenerateTable(Node node) throws Exception
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
                        EnterSymbol("deck", (((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling));
                    case "NumberDcl" :
                        EnterSymbol("number", (((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling));
                    case "CardDcl" :
                        EnterSymbol("card", (((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling));
                    case "HandDcl" :
                        EnterSymbol("hand", (((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling));
                    case "EnumDcl" :
                        EnterSymbol("enum", (((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling));
                    case "StringDcl" :
                        EnterSymbol("string", (((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling));
                    case "FlagDcl" :
                        EnterSymbol("flag", (((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling));
                }
        }
        for (Node child : node.GetChildren())
        {
            GenerateTable(child);
        }
    }

    private void OpenScope()
    {
        ScopeTable table = new ScopeTable();
        table.previous = stack.peek();
        stack.peek().subScopes.add(table);
        stack.push(table);
    }

    private void CloseScope()
    {
        stack.pop();
    }

    public Symbol RetrieveSymbol(String name)
    {
        ScopeTable current = stack.peek();
        while(current.previous != null)
        {
            if (current.table.get(name) != null)
            {
                return current.table.get(name);
            }
            else
            {
                current = current.previous;
            }
        }
        return null;
    }

    public void EnterSymbol(String type, String name) throws Exception
    {
        if (DeclaredLocally(name))
        {
            throw new Exception("Dublicate Definition of " + name);
        }
        else
        {
            stack.peek().table.put(name, new Symbol(name, type));
        }
    }
    public boolean DeclaredLocally(String name)
    {
        return stack.peek().table.get(name) != null;
    }
}
