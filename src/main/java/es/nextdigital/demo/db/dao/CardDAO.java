package es.nextdigital.demo.db.dao;

import es.nextdigital.demo.db.entities.CardEntity;
import es.nextdigital.demo.db.entities.MovementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardDAO extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByCardNumber(String cardNumber);

}
