package com.pb.hiring.controllers;

import static java.lang.String.format;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pb.hiring.model.Job;
import com.pb.hiring.model.Skill;

@Controller
public class JobsController {
    private final Log logger = LogFactory.getLog( getClass() );
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public void setSessionFactory(final SessionFactory sf) {
        sessionFactory = sf;
    }

    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/jobs")
    public String listJobs(final ModelMap modelMap) {
        final List<Job> jobs = sessionFactory.getCurrentSession()
            .createCriteria(Job.class)
            .addOrder( Order.desc("creationDate") )
            .list();
        modelMap.addAttribute("jobs", jobs);
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
        final Job job = (Job) sessionFactory.getCurrentSession().createCriteria(Job.class)
            .setFetchMode("skills", FetchMode.JOIN)
            .add( Restrictions.idEq(jobId) )
            .uniqueResult();
        modelMap.addAttribute("job", job);
        final List<Skill> skills = sessionFactory.getCurrentSession()
            .createCriteria( Skill.class )
                .addOrder( Order.asc("title").ignoreCase() )
                .list();
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
        job.getSkills().add(skill);
        return "redirect:/app/jobs/" + job.getJobId();
    }
}
