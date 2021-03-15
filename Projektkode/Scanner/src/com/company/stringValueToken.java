package com.company;

public class stringValueToken extends Token{
    String value;
    public stringValueToken(String avalue){
        super(tokenIdentifierEnum.stringValueToken);
        value = avalue;
    }
}
