package com.pb.hiring.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pb.hiring.model.Candidate;

public class ModelQueryHelperImpl implements ModelQueryHelper {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Criteria allCandidates() {
        return sessionFactory.getCurrentSession()
            .createCriteria(Candidate.class)
            .addOrder( Order.desc("creationDate") );
    }

    @Override
    @Transactional
    public Criteria allJobs() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public Criteria candidateById(final Long candidateId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public Criteria jobById(final Long jobId) {
        // TODO Auto-generated method stub
        return null;
    }

}
