package com.pb.hiring.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A Candidate is a person who may be evaluated for a Job.
 * 
 * @author pbourke
 */
@Entity
@Table(name="candidates")
@SequenceGenerator(name="candidate_id_seq", sequenceName="candidate_id_seq", allocationSize=1)
public class Candidate {
    @Id
    @Column(name="candidate_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="candidate_id_seq")
    private Long candidateId;
    
    private String name;
    
    private String email;
    
    @ManyToMany(cascade=CascadeType.PERSIST, targetEntity=Job.class)
    @JoinTable(name="candidates_jobs",
            joinColumns=@JoinColumn(name="candidate_id"), inverseJoinColumns=@JoinColumn(name="job_id"))
    @OrderBy("creationDate")
    private List<Job> jobs = new ArrayList<Job>();

    @Basic
    @Column(name="creation_date", updatable=false, nullable=false)
    private Date creationDate = new Date();
    
    @Version
    @Column(name="record_version_number")
    private Long recordVersionNumber;
    
    public Long getCandidateId() {
        return candidateId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    
    public Long getRecordVersionNumber() {
        return recordVersionNumber;
    }

    public List<Job> getJobs() {
        return jobs;
    }
    
    public void addJob(final Job job) {
        jobs.add(job);
    }    
}