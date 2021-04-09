package com.company;

public class nonZeroNumToken extends Token{
    int value;

    public nonZeroNumToken(int line, int avalue){
        super(line);
        value = avalue;
    }
}
