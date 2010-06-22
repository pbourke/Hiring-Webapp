package com.pb.hiring.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A Job holds information relating to a single Job posting
 * in the system
 * 
 * @author pbourke
 */
@Entity
@Table(name="jobs")
@SequenceGenerator(name="job_id_seq", sequenceName="job_id_seq")
public class Job {
    @Id
    @Column(name="job_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="job_id_seq")
    private Long jobId;
    
    private String title;
    
    private String description;
    
    @Basic
    @Column(name="creation_date", updatable=false, nullable=false)
    private Date creationDate = new Date();
    
    @Version
    @Column(name="record_version_number")
    private Long recordVersionNumber;
    
    public Long getJobId() {
        return jobId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    
    public Long getRecordVersionNumber() {
        return recordVersionNumber;
    }

}