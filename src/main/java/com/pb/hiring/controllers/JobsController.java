package com.pb.hiring.controllers;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pb.hiring.model.Job;

@Controller
public class JobsController {
    private SessionFactory sessionFactory;
    
    @Autowired
    public void setSessionFactory(final SessionFactory sf) {
        sessionFactory = sf;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/jobs")
    public String listJobs(final ModelMap modelMap) {
        final List<Job> jobs = sessionFactory.getCurrentSession()
            .createCriteria(Job.class)
            .addOrder( Order.desc("creationDate") )
            .list();
        modelMap.addAttribute("jobs", jobs);
        return "jobs/list";
    }

    @RequestMapping(method=RequestMethod.POST, value="/jobs")
    public String addJob(final Job job, final ModelMap modelMap) {
        sessionFactory.getCurrentSession().saveOrUpdate(job);
        modelMap.addAttribute("job", job);
        return "jobs/view";
    }
}
