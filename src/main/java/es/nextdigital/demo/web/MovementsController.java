package es.nextdigital.demo.web;

import es.nextdigital.demo.core.services.MovementService;
import es.nextdigital.demo.db.entities.MovementsEntity;
import es.nextdigital.demo.db.mappers.MovementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class MovementsController {

    @Autowired
    private MovementService movementService;

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovementDTO>> getMovementsByUserId(@PathVariable Long cuentaId) {
        return ResponseEntity.ok(movementService.getAllByCuentaId(cuentaId));
    }

    @PatchMapping("withdraw/{cardNumber}?amount={amount}")
    public ResponseEntity<Boolean> withdrawByCardNumber(@PathVariable String cardNumber, @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(movementService.getMoneyFromCardNumber(cardNumber, amount));
    }

    @PatchMapping("insert/{cardNumber}?amount={amount}")
    public ResponseEntity<Boolean> insertByCardNumber(@PathVariable String cardNumber, @RequestParam("amount") Integer amount) {
        return ResponseEntity.ok(true);
    }
}
