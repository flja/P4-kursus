package com.company;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.company.Tokens.*;

class ParserTest {

    @Test
    void matchTest() throws Exception {
        //arrange
        Token token = new cardsToken(3);
        String string = "CARDS";
        String wrong = "Setup";
        Parser parser = new Parser();
        String expectedException = "Expected Setup Token at line 3 But found a cards token";

        //act
        boolean result = parser.match(token,string);
        Throwable exception = assertThrows(Exception.class, () -> parser.match(token,wrong));

        //assert
        assertEquals(expectedException, exception.getMessage()); // Negativ test, som checker for om vi får den rette exception
        assertTrue(result==true); // Forventer at expected svarer til den aktuelle
        assertFalse(result==false); // Forventer at expected og aktuelle ikke er ens
    }
}