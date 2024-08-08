package es.nextdigital.demo.db.mappers;

import es.nextdigital.demo.models.EMovementType;
import lombok.Data;

@Data
public class MovementDTO {

    EMovementType type;
    private double value;

}
