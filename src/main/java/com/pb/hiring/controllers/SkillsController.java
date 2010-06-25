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

import com.pb.hiring.model.Skill;

@Controller
public class SkillsController {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/competencies")
    public String listCompetencies(final ModelMap modelMap) {
        final List<Skill> competencies = sessionFactory.getCurrentSession()
            .createCriteria( Skill.class )
                .addOrder( Order.desc("title") )
                .list();
        modelMap.put("competencies", competencies);
        modelMap.put("newCompetency", new Skill());
        return "skills/list";
    }

    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/competencies")
    public String addCompetency(final Skill competency) {
        sessionFactory.getCurrentSession().save(competency);
        return "redirect:competencies";
    }
}
