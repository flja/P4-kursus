package com.company.SymbolTable;

import com.company.AST.AST;
import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;

import java.util.Stack;

public abstract class Visitor
{
    Stack<ScopeTable> _stack = new Stack<ScopeTable>();
    ScopeTable _globalScope;
    Node _astTree;

    public Visitor(ScopeTable aTable, Node aAstTree)
    {
        _globalScope = aTable;
        _astTree = aAstTree;
        _stack.push(_globalScope);
    }

    public ScopeTable StartVisitor() throws Exception
    {
        RecursiveVisitor(_astTree);
        return _globalScope;
    }

    private void RecursiveVisitor(Node node) throws Exception
    {
        Visit(node);
        for (Node child : node.GetChildren())
        {
            RecursiveVisitor(child);
        }
    }

    void OpenScope()
    {
        ScopeTable table = new ScopeTable();
        table.previous = _stack.peek();
        _stack.peek().subScopes.add(table);
        _stack.push(table);
    }

    void CloseScope()
    {
        _stack.pop();
    }

    boolean CheckNonTerminalType(Node node, String nonTerminal)
    {
        if (node instanceof NonTerminalNode)
        {
            if (((NonTerminalNode) node).nonterminal == nonTerminal)
            {
                return true;
            }
        }
        return false;
    }

    void ReportError(String Message)
    {

    }

    public Symbol RetrieveSymbol(String name)
    {
        ScopeTable current = _stack.peek();
        while(current != null)
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

    public void EnterSymbol(Symbol symbol) throws Exception
    {
        System.out.println(symbol._id);

        if (DeclaredLocally(symbol._id))
        {
            throw new Exception("Dublicate Definition of " + symbol._id);
        }
        else
        {
            _stack.peek().table.put(symbol._id, symbol);
        }
    }

    public boolean DeclaredLocally(String name)
    {
        return _stack.peek().table.get(name) != null;
    }

    abstract void Visit(Node node) throws Exception;

}
