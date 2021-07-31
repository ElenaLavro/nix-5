package com.company.service;

import com.company.dao.impl.JPAOperation;
import com.company.model.dto.OperationDTO;
import com.company.model.entity.ExpenseCategory;
import com.company.model.entity.IncomeCategory;
import com.company.model.entity.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

public class OperationService {
    private static final Logger logger = LoggerFactory.getLogger(OperationService.class);
    private static JPAOperation operation;
    final Supplier<EntityManager> managerSupplier;


    public OperationService(JPAOperation operation, Supplier<EntityManager> ms) {
        OperationService.operation = operation;
        this.managerSupplier = ms;
    }

    public void createOperation(OperationDTO operationDTO) throws Exception {
        logger.debug("Start creating operation");
        EntityManager em = managerSupplier.get();
        try {
            var entity = new Operation();
            entity.setId(operationDTO.getId());
            entity.setSum(operationDTO.getSum());
            logger.debug("Determining the category of operation");
            if (operationDTO.getSum() > 0) {
                entity.setIncomeCategory(new IncomeCategory(operationDTO.getId_incomeCategory(), "low"));
            } else if (operationDTO.getSum() < 0) {
                entity.setExpenseCategory(new ExpenseCategory(operationDTO.getId_expenseCategory(), "Home office costs"));
            }
            logger.debug("Determining the category finished");
            entity.setDate(operationDTO.getDate());
            em.persist(entity);
        } catch (RuntimeException e) {
            logger.error("Adding operation failed");
            throw new Exception("Adding operation failed");
        }
    }

}
