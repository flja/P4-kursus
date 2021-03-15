package com.company;

public class idToken extends Token{
    String spelling;
    public idToken(String aspelling){
        super(tokenIdentifierEnum.idToken);
        spelling = aspelling;
    }
}
