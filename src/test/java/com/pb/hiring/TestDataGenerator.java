package com.pb.hiring;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pb.hiring.model.Candidate;
import com.pb.hiring.model.Interview;
import com.pb.hiring.model.Job;
import com.pb.hiring.model.Rating;
import com.pb.hiring.model.Skill;
import com.pb.hiring.model.User;

public class TestDataGenerator {
    @Autowired
    private SessionFactory sessionFactory;
    
    private final AtomicInteger skillCtr = new AtomicInteger(0);
    private final AtomicInteger jobCtr = new AtomicInteger(0);
    private final AtomicInteger candidateCtr = new AtomicInteger(0);
    private final AtomicInteger userCtr = new AtomicInteger(0);
    
    public Job job() {
        Job j = new Job();
        j.setDescription("Description for the job");
        j.setTitle("Job Title " + jobCtr.incrementAndGet());
        j.addSkill( skill() );
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
        c.setEmail("candidate"+seqNum+"@example.com");
        c.setName("Candidate " + seqNum);
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
    
    public User user() {
        final int seqNum = userCtr.incrementAndGet();
        User u = new User();
        u.setEmail("user" + seqNum + "@acme.com");
        u.setName("User " + seqNum);
        u.setPasswordPlaintext("password");
        sessionFactory.getCurrentSession().save(u);
        return u;
    }
    
    public Rating rating(final Job j, final Candidate c, final Skill s, final User u, int ratingVal) {
        final Rating rating = new Rating(j, s, c, u);
        rating.setRating(ratingVal);
        rating.setNotes("Rating notes");
        sessionFactory.getCurrentSession().save(rating);
        return rating;
    }
    
    public Interview interview(final Job j, final Candidate c, final User interviewer) {
        final Interview interview = new Interview(j, c, interviewer, new Date(), "Conference Room");
        sessionFactory.getCurrentSession().save(interview);
        return interview;
    }
    
    /**
     * Creates a Job, Candidate and User.
     */
    public Interview interview() {
        final Job job = job();
        return interview(job, candidate(job), user());
    }
}
