package com.company.CodeGenerator.TemplateCode;

public class CardClass {
    String Suit;
    String Facevalue;
    int Value;

    public CardClass(String aFacevalue, String aSuit, int aValue) {
        Suit = aSuit;
        Facevalue = aFacevalue;
        Value = aValue;
    }

    @Override
    public String toString() {
        return Facevalue + Suit;
    }
}
