package com.company;

public class nonZeroNumToken extends Token{
    int value;

    public nonZeroNumToken(int avalue){
        super(tokenIdentifierEnum.nonZeroNumToken);
        value = avalue;
    }
}
