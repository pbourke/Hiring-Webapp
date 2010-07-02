package com.pb.hiring.controllers;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pb.hiring.model.Candidate;
import com.pb.hiring.model.Job;
import com.pb.hiring.model.Rating;
import com.pb.hiring.service.ModelQueryHelper;

@Controller
public class CandidatesController {
    private final Log logger = LogFactory.getLog( getClass() );
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private ModelQueryHelper modelQueryHelper;

    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/candidates")
    public String listCandidates(final ModelMap modelMap) {
        modelMap.put("candidates", modelQueryHelper.allCandidates().list());
        modelMap.put("newCandidate", new Candidate());
        return "candidates/list";
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/candidates")
    public String addCandidate(final Candidate candidate) {
        sessionFactory.getCurrentSession().save(candidate);
        return "redirect:/app/candidates";
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/candidates/{candidateId}")
    public String getCandidate(@PathVariable final Long candidateId, final ModelMap modelMap) {
        Candidate candidate = (Candidate) modelQueryHelper.candidateById(candidateId).uniqueResult();
        
        modelMap.addAttribute("candidate", candidate);
        
        final List<Job> jobs = modelQueryHelper.allJobs().list();
        
        jobs.removeAll( candidate.getJobs() );
        
        modelMap.addAttribute("jobs", jobs);
        return "candidates/view";
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/candidates/{candidateId}/jobs")
    public String assignJob(@PathVariable final Long candidateId, @RequestParam("jobId") final Long jobId, final ModelMap modelMap) {
        getCandidate(candidateId, modelMap);
        final Candidate candidate = (Candidate) modelMap.get("candidate");
        final Job job = (Job) sessionFactory.getCurrentSession().get(Job.class, jobId);
        candidate.addJob(job);
        return "redirect:/app/candidates/"+candidate.getCandidateId();
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/candidates/{candidateId}/jobs/{jobId}")
    public String getCandidateJobPage(@PathVariable final Long candidateId, @PathVariable final Long jobId, final ModelMap modelMap) {
        // retrieve the candidate and add to model
        final Candidate candidate = (Candidate) modelQueryHelper.candidateById(candidateId).uniqueResult();
        modelMap.addAttribute("candidate", candidate);
        
        // retrieve the job and add to model
        final Job job = (Job) modelQueryHelper.jobById(jobId).uniqueResult();
        modelMap.addAttribute("job", job);
        
        // retrieve the ratings and add to model
        final List<Rating> ratings = modelQueryHelper.ratingsByCandidateAndJob(candidateId, jobId).list();
        modelMap.addAttribute("ratings", ratings);
        
        return "candidates/job";
    }
}
