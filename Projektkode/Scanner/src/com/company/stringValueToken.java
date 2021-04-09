package com.company;

public class stringValueToken extends Token{
    String value;
    public stringValueToken(int line, String avalue){
        super(line);
        value = avalue;
    }
}
