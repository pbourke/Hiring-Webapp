package com.pb.hiring.model;

import static java.lang.String.format;

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
 * Represents a scheduled interview in the system - captures its scheduled time
 * and location and any post-interview notes recorded by the interviewer.
 * 
 * @author pbourke
 */
@Entity
@Table(name="interviews")
@SequenceGenerator(name="interview_id_seq", sequenceName="interview_id_seq", allocationSize=1)
public class Interview {
    @Id
    @Column(name="interview_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="interview_id_seq")
    private Long interviewId;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="job_id")
    private Job job;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="candidate_id")
    private Candidate candidate;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User interviewer;
    
    private String notes;
    
    private String location;

    @Basic
    @Column(name="start_time")
    private Date startTime;
    
    @Basic
    @Column(name="creation_date", updatable=false, nullable=false)
    private Date creationDate = new Date();
    
    @Version
    @Column(name="record_version_number")
    private Long recordVersionNumber;
    
    private Interview() {
        // private constructor for Hibernate
    }
    
    public Interview(final Job j, final Candidate c, final User interviewer, final Date start, final String location) {
        this();
        
        if ( null == j || null == c || null == interviewer ) {
            throw new IllegalArgumentException(format("One of job=%s, candidate=%c or interviewer=%s was null.", j, c, interviewer));
        }
        this.job = j;
        this.candidate = c;
        this.interviewer = interviewer;
        this.startTime = start;
        this.location = location;
    }

    public Long getInterviewId() {
        return interviewId;
    }

    public Job getJob() {
        return job;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public User getInterviewer() {
        return interviewer;
    }
    public void setInterviewer(User interviewer) {
        this.interviewer = interviewer;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getRecordVersionNumber() {
        return recordVersionNumber;
    }
}
