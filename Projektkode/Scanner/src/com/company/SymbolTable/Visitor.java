package com.company.SymbolTable;

import com.company.AST.AST;
import com.company.AST.Node;
import com.company.AST.NonTerminalNode;

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
    }

    public void StartVisitor()
    {
        RecursiveVisitor(_astTree);

    }
    private void RecursiveVisitor(Node node)
    {
        Visit(node);
        for (Node child : node.GetChildren())
        {
            RecursiveVisitor(child);
        }
    }
    private void OpenScope()
    {
        ScopeTable table = new ScopeTable();
        table.previous = _stack.peek();
        _stack.peek().subScopes.add(table);
        _stack.push(table);
    }

    private void CloseScope()
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


    abstract void Visit(Node node) throws Exception;

}
