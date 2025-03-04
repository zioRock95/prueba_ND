package es.nextdigital.demo.core.services.impl;

import es.nextdigital.demo.core.services.CardService;
import es.nextdigital.demo.db.dao.CardDAO;
import es.nextdigital.demo.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardDAO cardDAO;

    // to be fair a different decryption from data sent from/to web and from/to bdd it's better
    @Autowired
    EncryptionUtil encryptionUtil;

    @Override
    public boolean activateById(String cardNumber, String pin) {
        var card = cardDAO.findByCardNumber(cardNumber).orElseThrow();

        if (card.isActive()) {
            return true; // or false? depends really on what should happen if we are trying to activate an already activated card
        }

        var decPin = encryptionUtil.decrypt(pin);
        if (decPin.equals(card.getPinNumber())) {
            card.setActive(true);
        } else {
            return false;
        }

        cardDAO.save(card);
        return true;
    }

    @Override
    public boolean changePinByCardNumber(String cardNumber, String oldPin, String newPin) {
        var card = cardDAO.findByCardNumber(cardNumber).orElseThrow();
        if (!card.isActive()) {
            return false; // or false? depends really on what should happen if we are trying to activate an already activated card
        }

        if (newPin.equals(oldPin)) {
            return true; // Technically it's updated :D (really should send a message warning the user)
        }

        var decOldPin = encryptionUtil.decrypt(oldPin);
        if (decOldPin.equals(card.getPinNumber())) {
            var decNewPin = encryptionUtil.decrypt(newPin);
            card.setPinNumber(decNewPin);
        } else {
            return false;
        }

        cardDAO.save(card);
        return true;
    }
}
