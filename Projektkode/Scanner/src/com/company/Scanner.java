package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    List<String> tokens = new ArrayList<String>();
    int current;
    String line;

    public String lexer() throws IOException
    {
        FileReader fr = new FileReader("C:\\Users\\Alexander Droob\\Desktop\\SW4\\Test.txt");
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null)
        {
            scanLine();
        }
        fr.close();
        return "";
    }
    public void scanLine()
    {
        String word;
        for ( current = 0 ; current < line.length() ; current++)
        {
            switch (line.charAt(current))
            {
                //single char tokens:
                case '{' : addtoken("lbrace");
                    break;
                case '}' : addtoken("rbrace");
                    break;
                case '(' : addtoken("lparen");
                    break;
                case ')' : addtoken("rparen");
                    break;
                case '+' : addtoken("plus");
                    break;
                case '-' : addtoken("hyphen");
                    break;
                case '"' : addtoken("quote");
                    break;
                case ',' : addtoken("comma");
                    break;
                case '.' : addtoken("dot");
                    break;
                case ';' : addtoken("semicolon");
                    break;
                case '*' : addtoken("star");
                    break;
                case '/' : addtoken("slash");
                    break;
                case '?' : addtoken("questionmark");
                    break;
                case ':' : addtoken("lessthan");
                    break;

                //no longer single char
                case '<' : addtoken(match('=') ? "lessthanorequal" : "lessthan");
                    break;
                case '>' : addtoken(match('=') ? "greaterthanorequal" : "greaterthan");
                    break;
                case '=' : addtoken(match('=') ? "equal" : "assign");
                    break;
                case '!' : addtoken(match('=') ? "notequal" : "not");
                    break;
                case ' ' :
                    break;
                case '\n':
                    break;
                default :
                    hsajdhkasdh.dfhasjkhfas
            }
        }
    }

    public void handleNumber(char digit)
    {
        if (Character.getNumericValue(digit) == 0)
        {
            addtoken("0");
        }
        else
        {
            addtoken("NonZeronNum");
        }
    }

    public void handleLetter(char letter)
    {
        String word = "";
        int tempcurrent = current;
        while(Character.toString(line.charAt(tempcurrent)).matches("[a-zA-Z0-9_]"))
        {
            word += line.charAt(current);
            tempcurrent++;
        }

        current = current + word.length() - 1;
        switch (word)
        {
            case "Define Cards" : addtoken("cardsdef");
                break;
            case "Define Player" : addtoken("playerdef");
                break;
            case "Define Table" : addtoken("tabledef");
                break;
            case "Setup" : addtoken("setup");
                break;
            case "Round" : addtoken("round");
                break;
            case "Turn" : addtoken("turn");
                break;
            case "Endcondition" : addtoken("endcondition");
                break;
            case "Deck" : addtoken("deck");
                break;
            case "Number" : addtoken("number");
                break;
            case "Card" : addtoken("card");
                break;
            case "Hand" : addtoken("hand");
                break;
            case "Enum" : addtoken("enum");
                break;
            case "String" : addtoken("string");
                break;
            case "Flag" : addtoken("flag");
                break;
            case "standard" : addtoken("standard");
                break;
            case "hearts" : addtoken("hearts");
                break;
            case "spades" : addtoken("spades");
                break;
            case "diamonds" : addtoken("diamonds");
                break;
            case "clubs" : addtoken("clubs");
                break;
            case "joker" : addtoken("joker");
                break;
            case "heart" : addtoken("heart");
                break;
            case "diamond" : addtoken("diamond");
                break;
            case "club" : addtoken("club");
                break;
            case "spade" : addtoken("spade");
                break;
            case "True" : addtoken("true");
                break;
            case "False" : addtoken("false");
                break;
        }
    }

    public void addtoken(String token)
    {
        tokens.add(token);
    }

    public boolean match(char expected)
    {
        if (line.charAt(current +1) != expected)
        {
            return false;
        }

        current++;
        return true;

    }
}
