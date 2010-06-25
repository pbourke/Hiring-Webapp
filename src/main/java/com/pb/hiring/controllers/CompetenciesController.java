package com.pb.hiring.controllers;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pb.hiring.model.Competency;

@Controller
public class CompetenciesController {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/competencies")
    public String listCompetencies(final ModelMap modelMap) {
        final List<Competency> competencies = sessionFactory.getCurrentSession()
            .createCriteria( Competency.class )
                .addOrder( Order.desc("title") )
                .list();
        modelMap.put("competencies", competencies);
        modelMap.put("newCompetency", new Competency());
        return "competencies/list";
    }

    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/competencies")
    public String addCompetency(final Competency competency) {
        sessionFactory.getCurrentSession().save(competency);
        return "redirect:competencies";
    }
}
