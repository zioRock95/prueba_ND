package es.nextdigital.demo.db.dao;

import es.nextdigital.demo.db.entities.CuentasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentasDAO extends JpaRepository<CuentasEntity, Long> {

    Optional<CuentasEntity> findById(Long userId);

}
