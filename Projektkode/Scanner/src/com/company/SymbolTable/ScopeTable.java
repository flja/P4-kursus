package com.company.SymbolTable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

public class ScopeTable
{
    ScopeTable previous = null;
    Hashtable<String, Symbol> table = new Hashtable<String, Symbol>();
    List<ScopeTable> subScopes = new ArrayList<ScopeTable>();
}
