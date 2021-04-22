package com.company;

import java.util.*;

public class Deck extends CardFunctionality
{

    public Deck(Stack<Card> cards)
    {
        cardStack = cards;
    }


    public void Shuffle()
    {
        Collections.shuffle(cardStack);
    }
}
