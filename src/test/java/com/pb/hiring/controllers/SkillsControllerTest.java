package com.pb.hiring.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.pb.hiring.model.Skill;

@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml", "classpath:com/pb/hiring/unit-test-beans.xml"})
public class SkillsControllerTest {
    @Autowired(required=true)
    private SkillsController competenciesController;
    
    @BeforeClass
    public static void setUpLog4J() {
        BasicConfigurator.configure();
    }
    
    @Test
    public void testEmptyCompetenciesList() {
        final ModelMap resultMap = new ModelMap();
        assertEquals("skills/list", competenciesController.listCompetencies(resultMap));
        final List<Skill> competencies = (List<Skill>) resultMap.get("skills");
        assertNotNull( competencies );
        assertTrue(competencies.isEmpty() );
        assertTrue(resultMap.get("newSkill") instanceof Skill);
    }
    
    @Test
    public void testAddCompetency() {
        assertTrue( listCompetencies().isEmpty() );
        final Skill c = new Skill();
        c.setDescription("some Description");
        c.setTitle("Some Title");
        assertEquals( "redirect:skills", competenciesController.addCompetency(c) );
        final List<Skill> competencies = listCompetencies();
        assertFalse( competencies.isEmpty() );
        assertEquals( "some Description", competencies.get(0).getDescription() );
        assertEquals( "Some Title", competencies.get(0).getTitle() );
        assertNotNull( competencies.get(0).getCompetencyId() );
        assertNotNull( competencies.get(0).getCreationDate() );
        assertNotNull( competencies.get(0).getRecordVersionNumber() );        
    }
    
    private List<Skill> listCompetencies() {
        final ModelMap resultMap = new ModelMap();
        competenciesController.listCompetencies(resultMap);
        return (List<Skill>) resultMap.get("skills");
    }
}
