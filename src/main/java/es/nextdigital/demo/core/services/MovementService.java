package es.nextdigital.demo.core.services;

import es.nextdigital.demo.db.mappers.MovementDTO;

import java.util.List;

public interface MovementService {

    List<MovementDTO> getAllByCuentaId(Long idCuenta);
}
