package com.company.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class FunctionSymbol extends Symbol
{
    String _returnType;
    List<String> _Parameters = new ArrayList<String>();
    public FunctionSymbol(String aId, String aType, String aReturnType, List<String> aParameters)
    {
        super(aId, aType);
        _returnType = aReturnType;
        _Parameters = aParameters;
    }
}
