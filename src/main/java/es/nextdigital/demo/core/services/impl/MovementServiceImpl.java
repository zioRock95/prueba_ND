package es.nextdigital.demo.core.services.impl;

import es.nextdigital.demo.core.services.MovementService;
import es.nextdigital.demo.db.dao.CardDAO;
import es.nextdigital.demo.db.dao.CuentasDAO;
import es.nextdigital.demo.db.dao.MovementsDAO;
import es.nextdigital.demo.db.entities.CardEntity;
import es.nextdigital.demo.db.entities.MovementsEntity;
import es.nextdigital.demo.db.mappers.MovementDTO;
import es.nextdigital.demo.models.ECardType;
import es.nextdigital.demo.models.EMovementType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    MovementsDAO movementsDAO;

    @Autowired
    CardDAO cardDAO;

    @Autowired
    CuentasDAO cuentasDAO;

    @Autowired
    ModelMapper modelMapper;

    @Value("${custom.bank.name}")
    private String bankName;


    @Override
    public List<MovementDTO> getAllByCuentaId(Long idCuenta) {
        var movementsEntitiyList = movementsDAO.findByCuentaId(idCuenta);
        return movementsEntitiyList.stream().map(m -> this.modelMapper.map(m, MovementDTO.class)).toList();
    }

    @Override
    public Boolean getMoneyFromCardNumber(String cardNumber, Integer amount) {
        var card = cardDAO.findByCardNumber(cardNumber).orElseThrow();
        if (!card.isActive()) {
            return false;
        }
        var cuenta = card.getCuentasEntity();

        var floatAmount = amount.doubleValue();
        var otherBank = false;

        if (!card.getBankEntity().getName().equals(this.bankName)) {
            floatAmount = amount.doubleValue() * card.getBankEntity().getCommissionFee();
            otherBank = true;
        }
        if (ECardType.DEBIT.equals(card.getCardType()) && cuenta.getBalance() < amount) {
            return false;
        }

        // not pretty, no time to do it properly
        if (ECardType.CREDIT.equals(card.getCardType()) && cuenta.getBalance() + cuenta.getMaxAmount() < amount) {
            return false;
        }


        cuenta.setBalance(cuenta.getBalance() - floatAmount);
        var movements = card.getCuentasEntity().getMovements();
        movements.add(new MovementsEntity(cuenta, EMovementType.CASH_OUT, floatAmount));

        cuenta.setMovements(movements);
        cuentasDAO.save(cuenta);

        if (otherBank) {
            // logic for commission to other bank
            // to be fair in a realistic env I think commissions are handled differently
            // like literally a different operation, in that case the total amount check is ok, but the subtraction of money should be handled differently
        }

        return true;
    }

    @Override
    public Boolean putMoneyFromCardNumber(String cardNumber, Integer amount) {
        var card = cardDAO.findByCardNumber(cardNumber).orElseThrow();
        if (!card.isActive() || !card.getBankEntity().getName().equals(this.bankName)) {
            return false;
        }

        var cuenta = card.getCuentasEntity();
        cuenta.setBalance(cuenta.getBalance() + amount);
        cuentasDAO.save(cuenta);

        return true;
    }
}
