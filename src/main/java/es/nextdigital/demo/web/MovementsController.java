package es.nextdigital.demo.web;

import es.nextdigital.demo.core.services.MovementService;
import es.nextdigital.demo.db.entities.MovementsEntity;
import es.nextdigital.demo.db.mappers.MovementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class MovementsController {

    @Autowired
    private MovementService movementService;

    @GetMapping("/user/{cuentaId}")
    public ResponseEntity<List<MovementDTO>> getMovementsByUserId(@PathVariable Long cuentaId) {
        return ResponseEntity.ok(movementService.getAllByCuentaId(cuentaId));
    }
}
