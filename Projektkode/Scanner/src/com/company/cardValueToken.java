package com.company;

public class cardValueToken extends Token{
    char facevalue;
    char suit;
    public cardValueToken(int line, char afacevalue, char asuit){
        super(line);
        facevalue = afacevalue;
        suit = asuit;
    }
}
