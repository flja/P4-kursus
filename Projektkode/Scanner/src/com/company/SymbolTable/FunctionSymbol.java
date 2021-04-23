package com.company.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class FunctionSymbol extends Symbol
{
    String _returnType;
    List<String> _parameters = new ArrayList<String>();
    public FunctionSymbol(String aId, String aType, String aReturnType, List<String> aParameters)
    {
        super(aId, aType);
        _returnType = aReturnType;
        _parameters = aParameters;
    }
    @Override
    public String ToString()
    {
        String parameters = " (";
        for (String item : _parameters)
        {
            parameters += item + ", ";
        }
        parameters = parameters.substring(0,parameters.length() - 2) + ")";
        return _id + ", " + _returnType + " " + _type + parameters ;
    }
}
