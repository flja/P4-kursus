package com.company;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import com.company.Tokens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Scanner1Test {


    @Test
    void handleKeywordsTest() throws Exception {
        //arrange
        String word = "Cards";
        Scanner1 scanner = new Scanner1();
        List<Token> Fakelist = scanner.Lexer();
        Token expectedToken = new cardsToken(3);
        //act
        Token token = scanner.HandleKeywords(word);
        int line = token.line;
        //assert
        assertEquals(expectedToken.line,token.line);

    }

    @Test
    void handleValue(){
        //arrange

        //act

        //assert
    }

    @Test
    void addTokenTest(){
        //arrange
        Scanner1 scanner = new Scanner1();
        Token token = new cardsToken(3);
        List<Token> expectedTokens = new ArrayList<Token>();
        List<Token> wrongTokens = new ArrayList<Token>();
        expectedTokens.add(token);

        //act
        scanner.AddToken(token);

        //assert
        assertEquals(expectedTokens,scanner.tokens);
        assertTrue(wrongTokens!=scanner.tokens);

    }
}