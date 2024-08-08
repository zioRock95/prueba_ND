package es.nextdigital.demo.db.entities;


import es.nextdigital.demo.models.EMovementType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "movement_table")
public class MovementsEntity implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    CuentasEntity cuenta;

    @Column(nullable = false)
    EMovementType type;

    @Column(nullable = false)
    private double value;

}
