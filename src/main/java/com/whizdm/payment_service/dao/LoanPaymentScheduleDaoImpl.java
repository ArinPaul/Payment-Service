package com.whizdm.payment_service.dao;

import com.whizdm.payment_service.entity.LoanPaymentSchedule;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LoanPaymentScheduleDaoImpl implements LoanPaymentScheduleDao{

    private EntityManager entityManager;

    @Autowired
    public LoanPaymentScheduleDaoImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void saveInitialSchedule(LoanPaymentSchedule loanPaymentSchedule) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(loanPaymentSchedule);
    }

    @Override
    @Transactional
    public void updateLoanPaymentSchedule() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("");
    }

    @Override
    @Transactional
    public List<LoanPaymentSchedule> retrieveLoanPayment(long theLoanApplicationId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("from loan_payment_schedule where loan_application_id =: Id");
        theQuery.setParameter("Id",theLoanApplicationId);
        List<LoanPaymentSchedule> list = theQuery.getResultList();
        return list;

    }
}
