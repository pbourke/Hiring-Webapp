package com.pb.hiring.model;

import static java.lang.String.format;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A Rating represents an assessment of a Candidate's Skill, for a given Job. 
 * The rating is an integer between 1 and 5. 
 * 
 * @author pbourke
 */
@Entity
@Table(name="ratings")
@SequenceGenerator(name="rating_id_seq", sequenceName="rating_id_seq", allocationSize=1)
public class Rating implements Serializable {
    @Id
    @Column(name="rating_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rating_id_seq")
    private Long ratingId;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="job_id")
    private Job job;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="skill_id")
    private Skill skill;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="candidate_id")
    private Candidate candidate;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User user;
    
    private int rating = 1;
    
    private String notes;
    
    @Basic
    @Column(name="creation_date", updatable=false, nullable=false)
    private Date creationDate = new Date();
    
    @Version
    @Column(name="record_version_number")
    private Long recordVersionNumber;
    
    public Rating(final Job j, final Skill s, final Candidate c, final User u) {
        this();
        if ( null == j || null == s || null == c || null == u ) {
            throw new IllegalArgumentException( format("None of job=%s, skill=%s, candidate=%s or user=%s may be null", j, s, c, u) );
        }
        job = j;
        skill = s;
        candidate = c;
        user = u;
    }
    
    private Rating() {
        // private constructor for Hibernate
    }

    public Long getRatingId() {
        return ratingId;
    }

    public Job getJob() {
        return job;
    }

    public Skill getSkill() {
        return skill;
    }

    public Candidate getCandidate() {
        return candidate;
    }
    
    public User getUser() {
        return user;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getRecordVersionNumber() {
        return recordVersionNumber;
    }
}
