package com.company.SymbolTable;

import java.util.*;

public class ScopeTable
{
    ScopeTable previous = null;
    HashMap<String, Symbol> table = new HashMap<String,Symbol>();
    List<ScopeTable> subScopes = new ArrayList<ScopeTable>();
}
