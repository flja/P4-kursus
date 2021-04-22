package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UtilClass
{

    char[] Suits = new char[]{'h','d','s','c'};
    String[] FaceValue = new String[]{"a","2","3","4","5","6","7","8","9","10","j","q","k"};


    public UtilClass()
    {
    }


    public boolean ValidateCard(String card)
    {
        if (card.matches("^([hHcCdDsS]([aAjJqQkK]|[2-9]|10))"))
            return true;
        else if (card.matches("^(([aAjJqQkK]|[2-9]|10)[hHcCdDsS])"))
            return true;
        else
            return false;
    }

    public Card ConvertStringToCard(String cardStr) throws Exception
    {
        Card card;

        if (cardStr.matches("^([hHcCdDsS]([aAjJqQkK]|[2-9]|10))"))
        {
            card = new Card(cardStr.charAt(0), cardStr.substring(1,cardStr.length()-1));
        }
        else if (cardStr.matches("^(([aAjJqQkK]|[2-9]|10)[hHcCdDsS])"))
        {
            if (cardStr.length() == 2)
            card = new Card(cardStr.charAt(1), cardStr.substring(0,1));
            else
                card = new Card(cardStr.charAt(2), cardStr.substring(0,2));
        }
        else
            throw new Exception();

        return card;
    }


    public Stack<Card> Standard(DefineCards defineCards) throws Exception
    {
         Stack<Card> CollectionOfCards=new Stack<Card>();

        for (int i = 0 ; i < Suits.length ; i++)
        {
            for (int j = 0 ; j < FaceValue.length ; j++)
            {
                CollectionOfCards.push(new Card(defineCards,Suits[i],FaceValue[j]));
            }
        }
        return CollectionOfCards;
    }
}
