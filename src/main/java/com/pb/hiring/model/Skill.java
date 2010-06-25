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
 * A Skill is a single skill which is evaluated for a Job.
 * 
 * @author pbourke
 */
@Entity
@Table(name="skills")
@SequenceGenerator(name="skill_id_seq", sequenceName="skill_id_seq", allocationSize=1)
public class Skill {
    @Id
    @Column(name="skill_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill_id_seq")
    private Long competencyId;
    
    private String title;
    
    private String description;
    
    @Basic
    @Column(name="creation_date", updatable=false, nullable=false)
    private Date creationDate = new Date();
    
    @Version
    @Column(name="record_version_number")
    private Long recordVersionNumber;

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

    public Long getCompetencyId() {
        return competencyId;
    }
    public Date getCreationDate() {
        return creationDate;
    }

    public Long getRecordVersionNumber() {
        return recordVersionNumber;
    }
}
