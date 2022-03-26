package com.whizdm.payment_service.dao;

import com.whizdm.payment_service.entity.LoanPaymentSchedule;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class DaoLoanPaymentScheduleImpl implements DaoLoanPaymentSchedule{

    private EntityManager entityManager;

    @Autowired
    public DaoLoanPaymentScheduleImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void saveInitialSchedule(LoanPaymentSchedule loanPaymentSchedule) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(loanPaymentSchedule);
        return;
    }

    @Override
    @Transactional
    public void updateLoanPaymentSchedule() {

        Session currentSession = entityManager.unwrap(Session.class);

    }

    @Override
    @Transactional
    public void retrieveLoanPayment() {

        Session currentSession = entityManager.unwrap(Session.class);

    }
}
