package com.company.ContextualAnalyzer;

import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.Parser;
import com.company.ShufflerSymbols.ShufflerSymbols;
import com.company.Tokens.idToken;

import java.lang.reflect.Type;

public class VisitorTypeCheck{
    ScopeTable _currentScope;
    Node _astTree;
    ShufflerSymbols _shufflerSymbols;
    public VisitorTypeCheck(ScopeTable aTable, Node aAstTree, ShufflerSymbols aShufflerSymbols) {

    }


    void Visit(Node node) throws Exception {
        if (node instanceof TerminalNode)
        {
            switch (Parser.GetName(((TerminalNode) node).terminal))
            {
                case "lBrace":
                {
                    for (ScopeTable s: _currentScope.subScopes)
                    {
                        if (!s.Visited)
                        {
                            _currentScope = s;
                            break;
                        }
                    }
                }
                case "rBrace":
                {
                  _currentScope.Visited = true;
                  _currentScope = _currentScope.previous;
                }
            }
        }
        if (node instanceof NonTerminalNode) {
            switch (((NonTerminalNode) node).nonterminal)
            {
                case "Assignment":
                    VisitAssignment(node);
                    break;
            }
        }
    }


    void RecursiveVisitor(Node node) throws Exception {
        Visit(node);
        for (Node child : node.GetChildren()) {
            if (!node.visited)
            {
                RecursiveVisitor(child);
            }
        }
    }
    public Symbol RetrieveSymbol(String name) {
        ScopeTable current = _currentScope;
        while (current != null) {
            if (current.table.get(name) != null) {
                return current.table.get(name);
            } else {
                current = current.previous;
            }
        }
        return null;
    }

    void VisitAssignment(Node node) throws Exception{
        node.visited = true;
        node.leftMostChild.rightSib.type = RetrieveSymbol(VisitObjectSpecifier(node.leftMostChild.rightSib))._type;
        String leftType = node.leftMostChild.rightSib.type;
        String rightType = VisitExpr(node.leftMostChild.rightSib.rightSib.rightSib);
        if (leftType != rightType)
        {
            throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild).terminal.line + ": type " + rightType + "Cannot be assigned to type " + leftType);
        }
        node.type = leftType;
    }

    String VisitExpr(Node node) throws Exception
    {
        node.visited = true;
        switch (((NonTerminalNode) node.leftMostChild).nonterminal)
        {
            case "AddExpr" :
                return VisitAddExpr(node.leftMostChild);
                break;
            case "LogicalExpr" :
                break;
            case "FunctionCall" :
                break;
        }
    }



    String VisitAddExpr(Node node) throws Exception
    {
        String leftType = VisitTerm(node.leftMostChild);
        String rightType = VisitAddExpr1(node.leftMostChild.rightSib);
        if (leftType.equals(rightType) || rightType.equals(""))
        {
            return leftType;
        }
        else {
            String operator;
            switch (Parser.GetName(((TerminalNode) node.leftMostChild.rightSib.rightSib.leftMostChild).terminal)) {
                case "star":
                    operator = " * ";
                    break;
                case "slash":
                    operator = " / ";
                    break;
                default:
                    operator = " mod ";
                    break;

            }
            throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild).terminal.line + ": cannot resolve \"" + leftType + operator + rightType);
        }
    }
    String VisitAddExpr1(Node node) throws Exception
    {
        if (node.leftMostChild == null)
        {
            return "";
        }
        else
        {
            String leftType = VisitTerm(node.leftMostChild.rightSib);
            String rightType = VisitAddExpr1(node.leftMostChild.rightSib.rightSib);
            if (leftType.equals(rightType))
            {
                if (Parser.GetName((((TerminalNode) node.leftMostChild).terminal)).equals("plus"))
                {
                    if (leftType.equals("Number") || leftType.equals("String"))
                    {
                        return leftType;
                    }
                }
                else
                {
                    if (leftType.equals("Number"))
                    {
                        return leftType;
                    }
                }
            }
            String operator;
            switch (Parser.GetName(((TerminalNode) node.leftMostChild.rightSib.leftMostChild).terminal))
            {
                case "plus":
                    operator = " + ";
                    break;
                default:
                    operator = " - ";
                    break;

            }
            throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild.rightSib.leftMostChild).terminal.line + ": cannot resolve \"" + leftType + operator + rightType + "\"");

        }
    }
    String VisitTerm(Node node) throws Exception
    {
        String leftType = VisitFactor(node.leftMostChild);
        String rightType = VisitTerm1(node.leftMostChild.rightSib);
        if (leftType.equals(rightType) || rightType.equals(""))
        {
            return leftType;
        }
        else
        {
            String operator;
            switch (Parser.GetName(((TerminalNode) node.leftMostChild.rightSib.leftMostChild).terminal))
            {
                case "star":
                    operator = " * ";
                    break;
                case "slash":
                    operator = " / ";
                    break;
                default:
                    operator = " mod ";
                    break;

            }
            throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild.rightSib.leftMostChild).terminal.line + ": cannot resolve \"" + leftType + operator +rightType + "\"");
        }
    }

    String VisitTerm1(Node node) throws Exception
    {
        if (node.leftMostChild == null)
        {
            return "";
        }
        else
        {
            String leftType = VisitFactor(node.leftMostChild.rightSib);
            String rightType = VisitTerm1(node.leftMostChild.rightSib.rightSib);
            if ((leftType.equals(rightType) && leftType.equals("Number")) || rightType.equals(""))
            {
                return leftType;
            }
            else
            {
                String operator;
                switch (Parser.GetName(((TerminalNode) node.leftMostChild.rightSib.rightSib.leftMostChild).terminal))
                {
                    case "star":
                        operator = " * ";
                        break;
                    case "slash":
                        operator = " / ";
                        break;
                    default:
                        operator = " mod ";
                        break;

                }
                throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild).terminal.line + ": cannot resolve \"" + leftType + operator + rightType + "\"");
            }
        }
    }
    String VisitFactor(Node node) throws Exception
    {
        if (node.leftMostChild instanceof TerminalNode)
        {
            return VisitAddExpr(node.leftMostChild.rightSib);
        }
        else
        {
            return VisitVal(node);

        }
    }
    String VisitVal(Node node)
    {
        if(((NonTerminalNode) node.leftMostChild).nonterminal.equals("ObjectSpecifier")) {
            return VisitObjectSpecifier(node);
        }
        else
        {
            return ((NonTerminalNode) node.leftMostChild).nonterminal;
        }
    }

    String VisitObjectSpecifier(Node node)
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
        else  if (node.leftMostChild instanceof NonTerminalNode)
        {
            switch (((NonTerminalNode) node.leftMostChild).nonterminal)
            {
                case "String":
                    id += "stringValue";
                    break;
                case "Number":
                    id += "numberValue";
                    break;
                case "Card":
                    id += "cardValue";
                    break;
            }
        }
        if (next != null)
        {
            id += VisitObjectSpecifier(next);
        }
        return id;
    }
}
