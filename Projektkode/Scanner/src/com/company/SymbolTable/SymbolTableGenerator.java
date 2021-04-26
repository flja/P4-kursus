package com.company.SymbolTable;

import com.company.AST.AST;
import com.company.ShufflerSymbols.LoadShufflerSymbols;
import com.company.ShufflerSymbols.PropertiesClass;
import com.company.ShufflerSymbols.ShufflerSymbols;

public class SymbolTableGenerator
{
    AST _ast;
    ScopeTable _globalScope = new ScopeTable();
    ShufflerSymbols _shufflerSymbols = new LoadShufflerSymbols().Load();


    public SymbolTableGenerator(AST aAST) throws Exception {
        _ast = aAST;

    }

    public ScopeTable GenerateSymbolTable() throws Exception
    {
        _ast.ResetVisit();
        addDefaultSymbols();
        _globalScope = new Visitor1(_globalScope, _ast.Root.GetChildren().get(8), _shufflerSymbols).StartVisitor();
        _globalScope = new Visitor2(_globalScope, _ast.Root.GetChildren().get(2), _shufflerSymbols).StartVisitor();
        _globalScope = new Visitor3(_globalScope, _ast.Root, _shufflerSymbols).StartVisitor();
        return _globalScope;
    }

    void addDefaultSymbols()
    {
        for (String item : _shufflerSymbols.DefaultSymbols.Cards.cards)
        {
            for (PropertiesClass p : _shufflerSymbols.DefaultSymbols.Cards.properties) {
                String id = item + "." + p.name;
                _globalScope.table.put(id ,new Symbol(id, p.type));
            }
        }
        for (String item : _shufflerSymbols.DefaultSymbols.Keywords.keywords)
        {
                _globalScope.table.put(item ,new Symbol(item, "keyword"));
        }
    }
}
//public class SymbolTableGenerator1
/*{
    Stack<ScopeTable> stack = new Stack<ScopeTable>();
    ScopeTable GlobalScope = new ScopeTable();

    public SymbolTableGenerator1()
    {
        stack.push(GlobalScope);
    }

    public void Visitor1(Node node) throws Exception
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
            Visitor1(child);
        }
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
}*/
