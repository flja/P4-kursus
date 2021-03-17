package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Scanner1
{
    int lineNum = 0;
    List<Token> tokens = new ArrayList<Token>();

    public Scanner1() {}

    public List<Token> Lexer() throws Exception
    {
        int current;
        String line;
        Token temp;
        Token temp2;
        String word = "";

        FileReader fr = new FileReader("C:\\Users\\Alexander Droob\\Desktop\\SW4\\Test.txt");
        BufferedReader br = new BufferedReader(fr);

        try
        {
            while ((line = br.readLine()) != null)
            {
                lineNum++;
                for (current = 0; current < line.length(); current++)
                {
                    temp = ScanSingleChar(line.charAt(current));
                    if (temp != null)
                    {  //If a single char token is found
                        //word handling:
                        if (word.length() > 0)
                        { //Check if we have created a word
                            temp2 = HandleKeywords(word); //if we have crafted a word check if it's a keyword
                            if (temp2 == null)
                            { // if it's not a keyword
                                temp2 = HandleValue(word); // then check if its a value
                                if (temp2 == null)
                                {  // if it's not a value
                                    temp2 = HandleId(word); // then check if it's an id
                                    if (temp2 == null)
                                    { // if it's not an id
                                        throw new Exception("Invalid"); // then throw error
                                    }
                                }
                            }
                            AddToken(temp2); // if no error is thrown add the token to the token list
                            word = ""; // reset word
                        }
                        if (!(temp instanceof WhitespaceToken))
                        { // if the single char found is not whitespace
                            if (current < line.length() - 1)
                            { //if we are not at the end of the file
                                temp2 = CheckCombined(line.charAt(current), line.charAt(current + 1), temp); // check for a combined token
                                current = temp == temp2 ? current : current + 1; //if a combined token is found count the current up one else just keep current
                                AddToken(temp2); //add the token to the token list
                            } else
                            {
                                AddToken(temp); //if we are at end of file then just add the token.
                            }
                        }

                    } else
                    {
                        word += IgnoreControlChar(line.charAt(current));
                    }
                }
                if (!word.isEmpty())
                {
                    temp2 = HandleKeywords(word);
                    if (!(temp2 == null))
                    {
                        AddToken(temp2);
                        word = "";
                    } else
                    {
                        throw new Exception("no end");
                    }

                }
            }
        } catch (Exception e)
        {
            br.close();
            throw e;
        }
        br.close();
        return tokens;
    }

    private String IgnoreControlChar(char c)
    {
        switch (c)
        {
            case 0x9: //tap
            case 0x20: // space
                return "";
        }
        return Character.toString(c);
    }

    private Token ScanSingleChar(char c)
    {
        Token token = null;
        switch (c)
        {
            //single char tokens:
            case '{':
                token = new lBraceToken();
                break;
            case '}':
                token = new rBraceToken();
                break;
            case '(':
                token = new lParenToken();
                break;
            case ')':
                token = new rParenToken();
                break;
            case '+':
                token = new plusToken();
                break;
            case '-':
                token = new hyphenToken();
                break;
            case ',':
                token = new commaToken();
                break;
            case '.':
                token = new dotToken();
                break;
            case ';':
                token = new semicolonToken();
                break;
            case '*':
                token = new starToken();
                break;
            case '/':
                token = new slashToken();
                break;
            case '?':
                token = new questionmarkToken();
                break;
            case ':':
                token = new colonToken();
                break;
            case ' ':
                token = new WhitespaceToken();
                break;
            case '<':
                token = new lessThanToken();
                break;
            case '>':
                token = new greaterThanToken();
                break;
            case '=':
                token = new assignToken();
                break;
            case '!':
                token = new notToken();
                break;
        }
        return token;
    }

    private Token CheckCombined(char current, char next, Token defaultVal)
    {
        Token token = defaultVal;

        switch (current)
        {
            case '<':
                token = next == '=' ? new lessThanOrEqualToken() : defaultVal;
                break;
            case '>':
                token = next == '=' ? new greaterThanOrEqualToken() : defaultVal;
                break;
            case '=':
                token = next == '=' ? new equalToken() : defaultVal;
                break;
            case '!':
                token = next == '=' ? new notEqualToken() : defaultVal;
                break;
        }
        return token;
    }

    private Token HandleValue(String value)
    {

        try
        {
            int i = Integer.parseInt(value);
            if (i == 0)
            {
                return new zeroToken();
            } else
            {
                return new nonZeroNumToken(i);
            }
        } catch (Exception e)
        {
        }

        if (value.matches("^([aAjJqQkK]|[2-9]|10)[hHcCsSdD]$"))
        {
            return new cardValueToken(value.toLowerCase().charAt(0), value.toLowerCase().charAt(1));
        }
        if (value.matches("^[\"][\\s\\S]*[\"]$"))
        {
            return new stringValueToken(value);
        }
        return null;
    }

    private Token HandleKeywords(String word)
    {
        Token token = null;
        switch (word)
        {
            case "Cards":
                token = new cardsToken();
                break;
            case "Player":
                token = new playerToken();
                break;
            case "Table":
                token = new tableToken();
                break;
            case "Define":
                token = new defineToken();
                break;
            case "Setup":
                token = new setupToken();
                break;
            case "Round":
                token = new roundToken();
                break;
            case "Turn":
                token = new turnToken();
                break;
            case "Endcondition":
                token = new endconditionToken();
                break;
            case "deck":
                token = new deckToken();
                break;
            case "number":
                token = new numberToken();
                break;
            case "card":
                token = new cardToken();
                break;
            case "hand":
                token = new handToken();
                break;
            case "enum":
                token = new enumToken();
                break;
            case "string":
                token = new stringToken();
                break;
            case "flag":
                token = new flagToken();
                break;
            case "standard":
                token = new standardToken();
                break;
            case "hearts":
                token = new heartsToken();
                break;
            case "spades":
                token = new spadesToken();
                break;
            case "diamonds":
                token = new diamondsToken();
                break;
            case "clubs":
                token = new clubsToken();
                break;
            case "joker":
                token = new jokerToken();
                break;
            case "True":
                token = new trueToken();
                break;
            case "False":
                token = new falseToken();
                break;
            case "actions":
                token = new actionsToken();
                break;
            case "if":
                token = new ifToken();
                break;
            case "else":
                token = new elseToken();
                break;
            case "endif":
                token = new endifToken();
                break;
            case "endelse":
                token = new endelseToken();
                break;
            case "endactions":
                token = new endactionsToken();
                break;
            case "assign":
                token = new assignSpecifierToken();
                break;
            case "neg":
                token = new negToken();
                break;
            case "void":
                token = new voidToken();
                break;
            case "switch":
                token = new switchToken();
                break;
            case "endswitch":
                token = new endSwitchToken();
                break;
        }
        return token;
    }

    private Token HandleId(String word)
    {
        return word.matches("^[a-zA-Z_][\\w]*$") ? new idToken(word) : null;
    }

    private void AddToken(Token token)
    {
        tokens.add(token);
    }
}
