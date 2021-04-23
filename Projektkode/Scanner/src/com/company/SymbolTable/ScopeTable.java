package com.company.SymbolTable;

import java.util.*;

public class ScopeTable
{
    ScopeTable previous = null;
    public HashMap<String, Symbol> table = new HashMap<String,Symbol>();
    public List<ScopeTable> subScopes = new ArrayList<ScopeTable>();
}
