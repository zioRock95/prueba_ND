package es.nextdigital.demo.db.dao;

import es.nextdigital.demo.db.entities.MovementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementsDAO extends JpaRepository<MovementsEntity, Long> {

    List<MovementsEntity> findByCuentaId(Long cuentaId);

}
