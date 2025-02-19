package com.banks.cards.controller;

import com.banks.cards.constants.CardsConstants;
import com.banks.cards.dtos.CardsDto;
import com.banks.cards.dtos.ResponseDTO;
import com.banks.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Cards in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping("/api/cards")
@AllArgsConstructor
@Validated
public class CardsController {
    private ICardsService cardsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED, CardsConstants.MESSAGE_201));
    }

    @GetMapping("/getCard")
    public ResponseEntity<ResponseDTO> getCard(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(HttpStatus.OK, CardsConstants.MESSAGE_200,cardsDto));
    }

    @PutMapping("/updateCard")
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = cardsService.updateCard(cardsDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(HttpStatus.OK, CardsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(HttpStatus.BAD_REQUEST, CardsConstants.MESSAGE_417_UPDATE));

    }

    @DeleteMapping("/deleteCard")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber) {
        boolean isUpdated = cardsService.deleteCard(mobileNumber);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(HttpStatus.OK, CardsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(HttpStatus.BAD_REQUEST, CardsConstants.MESSAGE_417_UPDATE));

    }

}
