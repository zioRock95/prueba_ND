package es.nextdigital.demo.db.entities;

import es.nextdigital.demo.utils.DbCypher;
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


    @Column(nullable = false)
    @Convert(converter = DbCypher.class)
    private String pinNumber;

    @Column(nullable = false)
    private boolean isActive;

    @JoinColumn(name = "bank_id", nullable = false)
    private BankEntity bankEntity;

    @Column(nullable = false)
    private ECardType cardType;
}
