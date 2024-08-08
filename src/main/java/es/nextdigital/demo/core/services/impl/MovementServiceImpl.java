package es.nextdigital.demo.core.services.impl;

import es.nextdigital.demo.core.services.MovementService;
import es.nextdigital.demo.db.dao.CardDAO;
import es.nextdigital.demo.db.dao.CuentasDAO;
import es.nextdigital.demo.db.dao.MovementsDAO;
import es.nextdigital.demo.db.mappers.MovementDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    MovementsDAO movementsDAO;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<MovementDTO> getAllByCuentaId(Long idCuenta) {
        var movementsEntitiyList = movementsDAO.findByCuentaId(idCuenta);
        return movementsEntitiyList.stream().map(m -> this.modelMapper.map(m, MovementDTO.class)).toList();
    }
}
