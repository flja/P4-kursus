package com.company.ContextualAnalyzer;

import com.company.AST.AST;
import com.company.AST.Node;
import com.company.AST.NonTerminalNode;
import com.company.AST.TerminalNode;
import com.company.Parser;
import com.company.ShufflerSymbols.ShufflerSymbols;
import com.company.Tokens.idToken;

import javax.lang.model.util.Elements;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisitorTypeCheck{
    ScopeTable _currentScope;
    Node _astTree;
    ShufflerSymbols _shufflerSymbols;

    public VisitorTypeCheck(ScopeTable aTable, Node aAstTree, ShufflerSymbols aShufflerSymbols) {
        _currentScope = aTable;
        _astTree = aAstTree;
        _shufflerSymbols = aShufflerSymbols;
    }

    void Visit(Node node) throws Exception {
        if (node instanceof TerminalNode)
        {
            switch (Parser.GetName(((TerminalNode) node).terminal).toLowerCase())
            {
                case "lbrace":
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
                break;
                case "rbrace":
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
    AST StartVisitor() throws Exception
    {
        RecursiveVisitor(_astTree);
        return new AST(_astTree);
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
                Symbol s = current.table.get(name);
                return current.table.get(name);
            } else {
                current = current.previous;
            }
        }
        return null;
    }

    void VisitAssignment(Node node) throws Exception{
        node.visited = true;
        String id = VisitObjectSpecifier(node.leftMostChild.rightSib);
        System.out.println("one");
        node.leftMostChild.rightSib.type = RetrieveSymbol(VisitObjectSpecifier(node.leftMostChild.rightSib))._type.toLowerCase();
        String leftType = node.leftMostChild.rightSib.type.toLowerCase();
        String rightType = VisitExpr(node.leftMostChild.rightSib.rightSib.rightSib).toLowerCase();
        if (!leftType.equals(rightType))
        {
            throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild).terminal.line + ": type " + rightType + " Cannot be assigned to type " + leftType);
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
            case "LogicalExpr" :
                return VisitLogicalExpr(node.leftMostChild);
            case "FunctionCall" :
                return VisitFunctionCall(node.leftMostChild);
        }
        return null;
    }

    String VisitFunctionCall(Node node) throws Exception
    {
        return VisitFunctionCall1(node);
    }
    String VisitFunctionCall1(Node node) throws Exception
    {
        List<String> parameterTypes = new ArrayList<String>();
        parameterTypes = VisitParameters(node.leftMostChild.rightSib.rightSib, parameterTypes);
        List<String> ExpectedTypes = FindExpectedParameterTypes(node.leftMostChild);
        int matchCnt = 0;
        for (int i = 0; i < ExpectedTypes.size(); i++)
        {
            if (ExpectedTypes.get(i).equals(parameterTypes.get(i)))
            {
                matchCnt++;
            }
            else throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild.rightSib).terminal.line + ": Parameter types does not match function parameter types");
        }
        if (matchCnt == parameterTypes.size() && matchCnt == ExpectedTypes.size())
        {
            String returnType = RetrieveSymbol(VisitObjectSpecifier(node.leftMostChild)).Type();
            returnType = returnType.substring(0, returnType.indexOf(" ") - 1);
            return returnType;
        }
        else throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild.rightSib).terminal.line + ": Number of parameters does not match function definition");
    }

    List<String> FindExpectedParameterTypes(Node node)
    {
        List<String> list = new ArrayList<String>();
        String funcType = RetrieveSymbol(VisitObjectSpecifier(node))._type;
        String parameterTypes = funcType.substring(funcType.indexOf("(") + 1, funcType.indexOf(")") - 1);
        parameterTypes = parameterTypes.substring(0, parameterTypes.lastIndexOf(";") - 1);
        list = Arrays.asList(parameterTypes.split(";"));
        return list;
    }
    List<String> VisitParameters(Node node, List<String> parameterTypes)
    {
        parameterTypes = VisitParameters1(node, parameterTypes);
        return parameterTypes;
    }
    List<String> VisitParameters1(Node node, List<String> parameterTypes)
    {
        parameterTypes.add(VisitVal(node.leftMostChild));
        parameterTypes = VisitParameters2(node.leftMostChild.rightSib, parameterTypes);
        return parameterTypes;
    }
    List<String> VisitParameters2(Node node, List<String> parameterTypes)
    {
        parameterTypes = VisitParameters1(node, parameterTypes);
        return parameterTypes;
    }
    String VisitLogicalExpr(Node node) throws Exception
    {
        String rightType = VisitLogicalTerm(node.leftMostChild.rightSib);
        String leftType = VisitLogicalExpr1(node.leftMostChild.rightSib.rightSib);
        if (leftType.equals("Flag")) {
            if (rightType.equals("") || rightType.equals(leftType) ) {
                return leftType;
            }
        }
        throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild).terminal.line + ": Illegal logical expression");
    }
    String VisitLogicalExpr1(Node node) throws Exception
    {
        if (node.leftMostChild == null)
        {
            return "";
        }
        String leftType = VisitLogicalTerm(node.leftMostChild.rightSib);
        String rightType = VisitLogicalExpr1(node.leftMostChild.rightSib.rightSib);
        if (leftType.equals(rightType) && leftType.equals("Flag"))
        {
            return leftType;
        }
        else
        {
            throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild).terminal.line + ": Illegal logical expression");
        }
    }

    String VisitLogicalTerm(Node node) throws Exception
    {
        String leftType = VisitAddExpr(node.leftMostChild);
        String rightType = VisitLogicalTerm1(node.leftMostChild.rightSib);
        if (rightType.equals(""))
        {
            return leftType;
        }
        else if (rightType.equals(leftType))
        {
            return leftType;
        }
        else
        {
            String operator = "";
            switch (Parser.GetName(((TerminalNode) node.leftMostChild.rightSib.leftMostChild.leftMostChild).terminal)) {
                case "lessthan":
                    operator = " < ";
                    break;
                case "greaterthan":
                    operator = " > ";
                    break;
                case "equal":
                    operator = " == ";
                    break;
                case "notequal":
                    operator = " != ";
                    break;
                case "lessthanorequal":
                    operator = " <= ";
                    break;
                case "greaterthanorequal ":
                    operator = " >= ";
                    break;

            }
            throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild.leftMostChild).terminal.line + ": cannot resolve \"" + leftType + operator + rightType);
        }
    }
    String VisitLogicalTerm1(Node node) throws Exception
    {
        if (node.leftMostChild == null)
        {
            return "";
        }
        else
        {
            String operator = Parser.GetName(((TerminalNode) node.leftMostChild.leftMostChild).terminal);
            String termType = VisitTerm(node.leftMostChild.rightSib);
            if (operator.equals("equal") || operator.equals("notequal"))
            {
                return termType;
            }
            else
            {
                if (termType.equals("Number"))
                {
                    return termType;
                }
                else
                {
                    switch (operator) {
                        case "lessthan":
                            operator = " < ";
                            break;
                        case "greaterthan":
                            operator = " > ";
                            break;
                        case "equal":
                            operator = " == ";
                            break;
                        case "notequal":
                            operator = " != ";
                            break;
                        case "lessthanorequal":
                            operator = " <= ";
                            break;
                        case "greaterthanorequal ":
                            operator = " >= ";
                            break;

                    }
                    throw new Exception("Error at line " + ((TerminalNode) node.leftMostChild.leftMostChild).terminal.line + ": cannot resolve \"" + operator + termType);
                }
            }
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
            String leftType = VisitTerm(node.leftMostChild.rightSib).toLowerCase();
            String rightType = VisitAddExpr1(node.leftMostChild.rightSib.rightSib).toLowerCase();
            if (leftType.equals(rightType))
            {
                if (Parser.GetName((((TerminalNode) node.leftMostChild).terminal)).equals("plus"))
                {
                    if (leftType.equals("number") || leftType.equals("string"))
                    {
                        return leftType;
                    }
                }
                else
                {
                    if (leftType.equals("number"))
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
        String leftType = VisitFactor(node.leftMostChild).toLowerCase();
        String rightType = VisitTerm1(node.leftMostChild.rightSib).toLowerCase();
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
            String leftType = VisitFactor(node.leftMostChild.rightSib).toLowerCase();
            String rightType = VisitTerm1(node.leftMostChild.rightSib.rightSib).toLowerCase();
            if ((leftType.equals(rightType) && leftType.equals("number")) || rightType.equals(""))
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
            return VisitAddExpr(node.leftMostChild.rightSib).toLowerCase();
        }
        else
        {
            return VisitVal(node.leftMostChild).toLowerCase();

        }
    }
    String VisitVal(Node node)
    {
        String type;
        if(((NonTerminalNode) node.leftMostChild).nonterminal.equals("ObjectSpecifier")) {
            type = RetrieveSymbol(VisitObjectSpecifier(node.leftMostChild))._type.toLowerCase();
        }
        else
        {
            type = ((NonTerminalNode) node.leftMostChild).nonterminal.toLowerCase();
        }
        node.type = type;
        node.visited = true;
        return type;
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
