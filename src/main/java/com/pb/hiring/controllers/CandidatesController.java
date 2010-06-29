package com.pb.hiring.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pb.hiring.model.Candidate;

@Controller
public class CandidatesController {
    private final Log logger = LogFactory.getLog( getClass() );
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public void setSessionFactory(final SessionFactory sf) {
        sessionFactory = sf;
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/candidates")
    public String listCandidates(final ModelMap modelMap) {
        final List<Candidate> candidates = sessionFactory.getCurrentSession()
            .createCriteria(Candidate.class)
            .addOrder( Order.desc("creationDate") )
            .list();
        modelMap.put("candidates", candidates);
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
        final Candidate candidate = (Candidate) sessionFactory.getCurrentSession().get(Candidate.class, candidateId);
        modelMap.addAttribute("candidate", candidate);
        return "candidates/view";
    }
}
