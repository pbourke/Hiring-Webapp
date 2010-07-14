package com.pb.hiring.controllers;

import static java.lang.String.format;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pb.hiring.controllers.util.UserRequestContext;
import com.pb.hiring.model.Candidate;
import com.pb.hiring.model.Interview;
import com.pb.hiring.model.Job;
import com.pb.hiring.model.User;
import com.pb.hiring.service.ModelQueryHelper;

@Controller
public class InterviewsController {
    private final Log logger = LogFactory.getLog( getClass() );
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private ModelQueryHelper modelQueryHelper;
    
    @Autowired
    private UserRequestContext userRequestContext;

    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/candidates/{candidateId}/jobs/{jobId}/interviews")
    public String addInterview(@PathVariable final Long candidateId, @PathVariable final Long jobId, @RequestParam("interviewerUserId") final Long interviewerUserId, @RequestParam("startTime") final Date startTime, @RequestParam("location") final String location) {
        // resolve the model objects
        final Candidate candidate = (Candidate) modelQueryHelper.candidateById(candidateId).uniqueResult();
        final Job job = (Job) modelQueryHelper.jobById(jobId).uniqueResult();
        final User interviewer = (User) modelQueryHelper.userById(interviewerUserId).uniqueResult();
        final Interview interview = new Interview(job, candidate, interviewer, startTime, location);
        
        logger.info( format("Adding an interview for job=%s, candidate=%s, interviewer=%s, startTime=%s, location=%s", 
                job, candidate, interviewer, startTime, location) );
        
        sessionFactory.getCurrentSession().save(interview);
        
        return "redirect:/app/candidates/"+candidateId+"/jobs/"+jobId;
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/candidates/{candidateId}/jobs/{jobId}/interviews/{interviewId}")
    public String getInterview(@PathVariable final Long candidateId, @PathVariable final Long jobId, @PathVariable final Long interviewId, final ModelMap modelMap) {
        final Interview interview = (Interview) modelQueryHelper.interviewByIdAndCandidateAndJob(interviewId, candidateId, jobId).uniqueResult();
        modelMap.addAttribute("interview", interview);
        return "interviews/view";
    }
}
