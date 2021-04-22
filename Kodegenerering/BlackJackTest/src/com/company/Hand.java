package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hand extends CardFunctionality
{
    boolean visible = false;
    UserInteraction userInteraction = new UserInteraction();
    UtilClass utilClass = new UtilClass();

    public Hand()
    {
    }

    public Card TakeByUserSelection(String printToUser) throws Exception
    {
        String s;
        
        do
        {
            s = userInteraction.readInput(printToUser);
        }
        while(!utilClass.ValidateCard(s));
        Card card = utilClass.ConvertStringToCard(s);
        for (Card c: cardStack)
        {
            if(c.equals(card))
            {
                cardStack.remove(c);
                card = c;
                break;
            }
        }
        return card;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
}
