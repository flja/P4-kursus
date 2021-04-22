package com.company;

import jdk.jshell.execution.Util;

public class Main {

    public static void main(String[] args) throws Exception {
        UtilClass utilClass = new UtilClass();
        DefineCards defaultCards = new DefineCards();
        Deck deck = new Deck(utilClass.Standard(defaultCards));
        defaultCards.SetNumericalValue(1, 11);
        utilClass.Standard(defaultCards);


        try {
            deck.Shuffle();
            for (int i = 0; i < deck.cardStack.size(); i++)
            {
                System.out.println(deck.cardStack.get(i).Suit+ " "+ deck.cardStack.get(i).FaceValue+" "+deck.cardStack.get(i).Value);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
