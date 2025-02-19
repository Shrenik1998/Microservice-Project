package com.banks.cards.exceptions;

public class CardAlreadyExistsException extends RuntimeException{
    public CardAlreadyExistsException(String msg){
        super(msg);
    }
}
