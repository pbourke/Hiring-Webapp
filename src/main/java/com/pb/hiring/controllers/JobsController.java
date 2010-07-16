package com.pb.hiring.controllers;

import static java.lang.String.format;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pb.hiring.model.Job;
import com.pb.hiring.model.Skill;
import com.pb.hiring.service.ModelQueryHelper;

@Controller
public class JobsController {
    private final Log logger = LogFactory.getLog( getClass() );
    
    @Autowired(required=true)
    private SessionFactory sessionFactory;
    
    @Autowired(required=true)
    private ModelQueryHelper modelQueryHelper;
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/jobs")
    public String listJobs(final ModelMap modelMap) {
        modelMap.addAttribute("jobs", modelQueryHelper.allJobs().list());
        modelMap.addAttribute("newJob", new Job());
        return "jobs/list";
    }

    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/jobs")
    public String addJob(final Job job) {
        sessionFactory.getCurrentSession().saveOrUpdate(job);
        return "redirect:jobs/" + job.getJobId();
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/jobs/{jobId}")
    public String getJob(@PathVariable final Long jobId, final ModelMap modelMap) {
        final Job job = (Job) modelQueryHelper.jobById(jobId).uniqueResult();
        modelMap.addAttribute("job", job);
        final List<Skill> skills = modelQueryHelper.allSkills().list();
        skills.removeAll( job.getSkills() );
        modelMap.addAttribute("skills", skills);        
        return "jobs/view";
    }

    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/jobs/{jobId}/skills")
    public String addSkill(@PathVariable final Long jobId, @RequestParam("skillId") final Long skillId, final ModelMap modelMap) {
        getJob(jobId, modelMap);
        final Job job = (Job) modelMap.get("job");
        logger.info( format("jobId=%d, skillId=%d", jobId, skillId) );
        final Skill skill = (Skill) sessionFactory.getCurrentSession().get(Skill.class, skillId);
        job.addSkill(skill);
        return "redirect:/app/jobs/" + job.getJobId();
    }

    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/jobs/{jobId}/skills/{skillId}")
    public String removeSkill(@PathVariable final Long jobId, @PathVariable("skillId") final Long skillId, @RequestParam("removeSkill") final boolean removeSkill) {
        final Job job = (Job) modelQueryHelper.jobById(jobId).uniqueResult();
        logger.info( format("jobId=%d, skillId=%d", jobId, skillId) );
        final Skill skill = (Skill) sessionFactory.getCurrentSession().get(Skill.class, skillId);
        
        if ( removeSkill ) {
            job.removeSkill(skill);
        }
        
        return "redirect:/app/jobs/" + job.getJobId();
    }

}
