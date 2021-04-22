package com.company;


import java.util.Locale;
import java.util.Objects;

public class Card{
    public char Suit;
    public String FaceValue;
    public int Value;

    public Card(char suit, String value) throws Exception
    {
        Suit = setSuit(suit);
        FaceValue = setFaceValue(value);
    }

    public Card(DefineCards defineCards, char suit, String value) throws Exception {
        Suit = setSuit(suit);
        FaceValue = setFaceValue(value);
        Value = SetValue(defineCards);
    }

    int SetValue(DefineCards defineCards) {
        int val = 0;
        switch (FaceValue)
        {
            case "a":
                val = 1;
            break;
            case "2":
                val = 2;
                break;
            case "3":
                val = 3;
                break;
            case "4":
                val = 4;
                break;
            case "5":
                val = 5;
                break;
            case "6":
                val = 6;
                break;
            case "7":
                val = 7;
                break;
            case "8":
                val = 8;
                break;
            case "9":
                val = 9;
                break;
            case "10":
                val = 10;
                break;
            case "j":
                val = 11;
                break;
            case "q":
                val = 12;
                break;
            case "k":
                val = 13;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return defineCards.ConvertFaceValueToNumericalValue(val);
    }

    char setSuit(char suit) throws Exception {
        if (suit == 'h' || suit == 'd' || suit == 's' || suit == 'c')
            return suit;
        else throw new Exception();
    }

    String setFaceValue(String value) throws Exception {
        if (value.matches("^([aAjJqQkK]|[2-9]|10)")) //VI MANGLER EN 10 I VORES KORTSPIL!
            return value.toLowerCase();
        else throw new Exception();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Suit == card.Suit && Objects.equals(FaceValue, card.FaceValue);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(Suit, FaceValue);
    }
}