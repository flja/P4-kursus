package com.company;

public abstract class Token {
    enum tokenIdentifierEnum {
        actionsToken,
        assignSpecifierToken,
        assignToken,
        cardsToken,
        cardToken,
        cardValueToken,
        clubsToken,
        colonToken,
        commaToken,
        deckToken,
        defineToken,
        diamondsToken,
        dotToken,
        elseToken,
        endactionsToken,
        endconditionToken,
        endelseToken,
        endifToken,
        enumToken,
        equalToken,
        falseToken,
        flagToken,
        greaterThanOrEqualToken,
        greaterThanToken,
        handToken,
        heartsToken,
        hyphenToken,
        idToken,
        ifToken,
        jokerToken,
        lBraceToken,
        lessThanOrEqualToken,
        lessThanToken,
        lParenToken,
        nonZeroNumToken,
        notEqualToken,
        notToken,
        numberToken,
        playerToken,
        plusToken,
        questionmarkToken,
        rBraceToken,
        roundToken,
        rParenToken,
        semicolonToken,
        setupToken,
        slashToken,
        spadesToken,
        standardToken,
        starToken,
        stringToken,
        stringValueToken,
        tableToken,
        trueToken,
        turnToken,
        WhitespaceToken,
        zeroToken,
        negToken,
        voidToken,
        switchToken,
        endSwitchToken}
    tokenIdentifierEnum identifier;

    public tokenIdentifierEnum Identifier(){
        return identifier;
    }
    public Token(tokenIdentifierEnum a){
        identifier = a;
    }
}

