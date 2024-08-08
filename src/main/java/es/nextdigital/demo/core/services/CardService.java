package es.nextdigital.demo.core.services;

public interface CardService {

    boolean activateById(String cardId, String pin);

    boolean changePinByCardNumber(String cardId, String oldPin, String newPin);
}
