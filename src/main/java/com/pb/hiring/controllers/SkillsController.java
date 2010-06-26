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
    @RequestMapping(method=RequestMethod.GET, value="/skills")
    public String listSkills(final ModelMap modelMap) {
        final List<Skill> skills = sessionFactory.getCurrentSession()
            .createCriteria( Skill.class )
                .addOrder( Order.asc("title").ignoreCase() )
                .list();
        modelMap.put("skills", skills);
        modelMap.put("newSkill", new Skill());
        return "skills/list";
    }

    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/skills")
    public String addSkill(final Skill skill) {
        sessionFactory.getCurrentSession().save(skill);
        return "redirect:skills";
    }
}
