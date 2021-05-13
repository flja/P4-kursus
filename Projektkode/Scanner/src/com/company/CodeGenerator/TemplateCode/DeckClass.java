package com.company.CodeGenerator.TemplateCode;

import java.util.ArrayList;
import java.util.List;

public class DeckClass {
    List<CardClass> cards;

    public int size = cards.size();
    public String visibility; //String or bool?
    public DeckClass(String[] aCards) throws Exception {
        GenerateDeck(aCards);
    }
    public DeckClass() throws Exception {
    }
    public DeckClass(String cards) throws Exception {
        String[] a = {cards};
        GenerateDeck(a);
    }


    void GenerateDeck(String[] Cards) throws Exception
    {
        cards = new ArrayList<CardClass>();
        for (String s : Cards) {
            switch (s) {
                case "standard":
                case "Standard":
                    cards.addAll(HelpMethods.CreateSuitDeck("h"));
                    cards.addAll(HelpMethods.CreateSuitDeck("c"));
                    cards.addAll(HelpMethods.CreateSuitDeck("d"));
                    cards.addAll(HelpMethods.CreateSuitDeck("s"));
                    break;
                case "Hearts":
                    cards.addAll(HelpMethods.CreateSuitDeck("h"));
                    break;
                case "Clubs":
                    cards.addAll(HelpMethods.CreateSuitDeck("c"));
                    break;
                case "Diamonds":
                    cards.addAll(HelpMethods.CreateSuitDeck("d"));
                    break;
                case "Spades":
                    cards.addAll(HelpMethods.CreateSuitDeck("s"));
                    break;
                default:
                    cards.add(HelpMethods.CreateCard(s));
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "DeckClass{" +
                "cards=" + cards +
                '}';
    }
}
