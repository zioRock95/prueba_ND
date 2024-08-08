package es.nextdigital.demo.web;

import es.nextdigital.demo.core.services.CardService;
import es.nextdigital.demo.core.services.MovementService;
import es.nextdigital.demo.db.mappers.MovementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardsController {

    @Autowired
    private CardService cardService;

    @GetMapping("/activate/{cardNumber}?pin={pin}")
    public ResponseEntity<Boolean> activateByCardNumber(@PathVariable String cardNumber, @RequestParam("pin") String pin) {
        return ResponseEntity.ok(cardService.activateById(cardNumber, pin));
    }

    @PatchMapping("/change/pin/{cardNumber}?pin={pin}&new={new}")
    public ResponseEntity<Boolean> changePinByCardNumber(@PathVariable String cardNumber, @RequestParam("pin") String oldPin, @RequestParam("new") String newPin) {
        return ResponseEntity.ok(cardService.changePinByCardNumber(cardNumber, oldPin, newPin));
    }

}
