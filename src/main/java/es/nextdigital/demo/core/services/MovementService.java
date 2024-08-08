package es.nextdigital.demo.core.services;

import es.nextdigital.demo.db.mappers.MovementDTO;

import java.util.List;

public interface MovementService {

    /**
     * Get all movements by cuenta id choosen by user at cajero
     * @param idCuenta the id of the cuenta
     * @return All movements for that cuenta id
     */
    List<MovementDTO> getAllByCuentaId(Long idCuenta);

    /**
     * Withdrow money from account.
     * No time to implement a decent exception handling logic to return codes/messages to FrontEnd.
     * @param cardNumber the card that is requesting the operation
     * @param amount the amount of money to withdraw
     * @return true if ok, false otherwise.
     */
    Boolean getMoneyFromCardNumber(String cardNumber, Integer amount);

    Boolean putMoneyFromCardNumber(String cardNumber, Integer amount);
}
