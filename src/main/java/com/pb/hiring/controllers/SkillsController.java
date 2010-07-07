package com.pb.hiring.controllers;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pb.hiring.model.Skill;
import com.pb.hiring.service.ModelQueryHelper;

@Controller
public class SkillsController {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private ModelQueryHelper modelQueryHelper;
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/skills")
    public String listSkills(final ModelMap modelMap) {
        modelMap.put("skills", modelQueryHelper.allSkills().list());
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
