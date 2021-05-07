package com.company;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import com.company.Tokens.*;
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
}