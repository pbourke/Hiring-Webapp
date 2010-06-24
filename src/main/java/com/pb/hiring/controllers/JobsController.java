package com.pb.hiring.controllers;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.pb.hiring.model.Job;

@Controller
public class JobsController {
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
            .add( Restrictions.idEq(jobId) ).uniqueResult();
        modelMap.addAttribute("job", job);
        return "jobs/view";
    }
}
