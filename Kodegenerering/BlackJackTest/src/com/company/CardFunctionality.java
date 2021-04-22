package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

abstract public class CardFunctionality
{
    //protected List<Card> cardsList = new ArrayList<Card>();
    Stack<Card> cardStack = new Stack<>();

    public CardFunctionality()
    {
    }

    public void DrawFrom(CardFunctionality deck, int amount)
    {
        Stack<Card> s = deck.TakeCard(amount);

        for(int i = 0 ; i < s.size() ; i++)
        {
            cardStack.push(s.pop());
        }
    }

    public Stack<Card> TakeCard(int amount)
    {
        Stack<Card> stack = new Stack<>();
        for (int i = 0 ; i < amount ; i++)
        {
            stack.push(cardStack.pop());
        }
        return stack;
    }
}
