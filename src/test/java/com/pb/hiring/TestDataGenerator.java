package com.pb.hiring;

import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pb.hiring.model.Candidate;
import com.pb.hiring.model.Job;
import com.pb.hiring.model.Rating;
import com.pb.hiring.model.Skill;

public class TestDataGenerator {
    @Autowired
    private SessionFactory sessionFactory;
    
    private final AtomicInteger skillCtr = new AtomicInteger(0);
    private final AtomicInteger jobCtr = new AtomicInteger(0);
    private final AtomicInteger candidateCtr = new AtomicInteger(0);
    
    public Job job() {
        Job j = new Job();
        j.setDescription("Description for the job");
        j.setTitle("Job Title " + jobCtr.incrementAndGet());
        j.getSkills().add( skill() );
        sessionFactory.getCurrentSession().save(j);
        sessionFactory.getCurrentSession().flush();
        return j;
    }
    
    public Skill skill() {
        Skill s = new Skill();
        s.setDescription("Description for the skill");
        s.setTitle("Skill Title " + skillCtr.incrementAndGet());
        sessionFactory.getCurrentSession().save(s);
        return s;
    }
    
    public Candidate candidate() {
        final int seqNum = candidateCtr.incrementAndGet();
        Candidate c = new Candidate();
        c.setEmail("person"+seqNum+"@example.com");
        c.setName("Person " + seqNum);
        sessionFactory.getCurrentSession().save(c);
        return c;
    }
    
    public Candidate candidate(final Job j) {
        final Candidate c = candidate();
        c.addJob(j);
        sessionFactory.getCurrentSession().save(c);
        sessionFactory.getCurrentSession().flush();
        return c;
    }
    
    public Rating rating(final Job j, final Candidate c, final Skill s, int ratingVal) {
        final Rating rating = new Rating(j, s, c);
        rating.setRating(ratingVal);
        rating.setNotes("Rating notes");
        sessionFactory.getCurrentSession().save(rating);
        return rating;
    }
    
}
