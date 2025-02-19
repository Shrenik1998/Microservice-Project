package com.banks.cards.service;

import com.banks.cards.dtos.CardsDto;

public interface ICardsService {
    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);


    boolean deleteCard(String mobileNumber);
}
