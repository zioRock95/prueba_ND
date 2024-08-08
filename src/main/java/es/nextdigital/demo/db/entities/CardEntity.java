package es.nextdigital.demo.db.entities;

import es.nextdigital.demo.models.ECardType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Data
@Getter
@Table(name = "card_table")
public class CardEntity implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentasEntity cuentasEntity;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    // implement encrypt decrypt on read/write operation
    // use key from ppty file that will read it from env var setup outside docker image
    @Column(nullable = false)
    private String pinNumber;

    @Column(nullable = false)
    private boolean isActive;

    @JoinColumn(name = "bank_id", nullable = false)
    private BankEntity bankEntity;

    @Column(nullable = false)
    private ECardType cardType;
}
