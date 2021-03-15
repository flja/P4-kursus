package com.company;

public class cardValueToken extends Token{
    char facevalue;
    char suit;
    public cardValueToken(char afacevalue, char asuit){
        super(tokenIdentifierEnum.cardValueToken);
        facevalue = afacevalue;
        suit = asuit;
    }
}
