package com.company.SymbolTable;

import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.Tokens.idToken;

import java.util.Stack;

public abstract class Visitor {
    Stack<ScopeTable> _stack = new Stack<ScopeTable>();
    ScopeTable _globalScope;
    Node _astTree;

    public Visitor(ScopeTable aTable, Node aAstTree) {
        _globalScope = aTable;
        _astTree = aAstTree;
        _stack.push(_globalScope);
    }

    public ScopeTable StartVisitor() throws Exception {
        RecursiveVisitor(_astTree);
        return _globalScope;
    }

    void RecursiveVisitor(Node node) throws Exception {
        Visit(node);
        for (Node child : node.GetChildren()) {
            RecursiveVisitor(child);
        }
    }

    void OpenScope() {
        ScopeTable table = new ScopeTable();
        table.previous = _stack.peek();
        _stack.peek().subScopes.add(table);
        _stack.push(table);
    }

    void CloseScope() {
        _stack.pop();
    }

    boolean CheckNonTerminalType(Node node, String nonTerminal) {
        if (node instanceof NonTerminalNode) {
            if (((NonTerminalNode) node).nonterminal == nonTerminal) {
                return true;
            }
        }
        return false;
    }

    void ReportError(String Message) {

    }

    public Symbol RetrieveSymbol(String name) {
        ScopeTable current = _stack.peek();
        while (current != null) {
            if (current.table.get(name) != null) {
                return current.table.get(name);
            } else {
                current = current.previous;
            }
        }
        return null;
    }

    void EnterSymbolToCurrentScope(Symbol symbol) throws Exception {
        AddSymbolToTable(symbol, _stack.peek());
    }

    public void EnterGlobalSymbol(Symbol symbol) throws Exception
    {
        AddSymbolToTable(symbol, _globalScope);
    }

    void AddSymbolToTable(Symbol symbol, ScopeTable scope) throws Exception
    {
        if (DeclaredLocally(symbol.Id()))
        {
            throw new Exception("Dublicate Definition of " + symbol.Id());

        } else
        {
            scope.table.put(symbol.Id(), symbol);
        }
    }
    Symbol EnterDclSymbolHelper(Node node, String type) throws Exception
    {
        return new Symbol((((idToken) ((TerminalNode) (node.leftMostChild.rightSib)).terminal).spelling), type);
    }

    public boolean DeclaredLocally(String name)
    {
        return _stack.peek().table.get(name) != null;
    }

    void AddPropertiesToTable(Symbol symbol, ScopeTable table)
    {

    }

    abstract void Visit(Node node) throws Exception;

}
