package com.pb.hiring.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pb.hiring.model.Candidate;
import com.pb.hiring.model.Job;
import com.pb.hiring.model.Rating;
import com.pb.hiring.model.Skill;

public class ModelQueryHelperImpl implements ModelQueryHelper {
    
    private transient final Log logger = LogFactory.getLog( getClass() );
    
    @Autowired(required=true)
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
    public Criteria candidateById(final Long candidateId) {
        return sessionFactory.getCurrentSession().createCriteria( Candidate.class )
            .setFetchMode("jobs", FetchMode.JOIN)
            .add( Restrictions.idEq(candidateId) );
    }

    @Override
    @Transactional
    public Criteria allJobs() {
        return sessionFactory.getCurrentSession()
            .createCriteria(Job.class)
            .addOrder( Order.desc("creationDate") );
    }

    @Override
    @Transactional
    public Criteria jobById(final Long jobId) {
        return sessionFactory.getCurrentSession().createCriteria(Job.class)
            .setFetchMode("skills", FetchMode.JOIN)
            .add( Restrictions.idEq(jobId) );
    }

    @Override
    @Transactional
    public Criteria allSkills() {
        return sessionFactory.getCurrentSession()
            .createCriteria( Skill.class )
            .addOrder( Order.asc("title").ignoreCase() );
    }

    @Override
    @Transactional
    public Criteria skillById(final Long skillId) {
        return sessionFactory.getCurrentSession().createCriteria(Skill.class)
            .add( Restrictions.idEq(skillId) );
    }

    @Override
    @Transactional
    public Criteria ratingsByCandidateAndJob(final Long candidateId, final Long jobId) {
        return sessionFactory.getCurrentSession().createCriteria( Rating.class )
            .add( Restrictions.eq("job.jobId", jobId) )
            .add( Restrictions.eq("candidate.candidateId", candidateId) );
    }
}