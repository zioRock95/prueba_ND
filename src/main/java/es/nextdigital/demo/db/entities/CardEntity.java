package es.nextdigital.demo.db.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "card_table")
public class CardEntity implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private String pinNumber;
}
