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

import com.pb.hiring.TestDataGenerator;
import com.pb.hiring.model.Skill;
import com.pb.hiring.service.ModelQueryHelper;

@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml", "classpath:com/pb/hiring/unit-test-beans.xml"})
public class SkillsControllerTest {
    @Autowired(required=true)
    private SkillsController skillsController;
    
    @Autowired(required=true)
    private ModelQueryHelper modelQueryHelper;
    
    @Autowired(required=true)
    private TestDataGenerator testData;
    
    @BeforeClass
    public static void setUpLog4J() {
        BasicConfigurator.configure();
    }
    
    @Test
    public void testEmptySkillsList() {
        assertTrue( "skills list is empty", modelQueryHelper.allSkills().list().isEmpty() );
        final ModelMap resultMap = new ModelMap();
        assertEquals("skills/list", skillsController.listSkills(resultMap));
        final List<Skill> skills = (List<Skill>) resultMap.get("skills");
        assertNotNull( skills );
        assertTrue(skills.isEmpty() );
        assertTrue(resultMap.get("newSkill") instanceof Skill);
    }
    
    @Test
    public void testSkillsListContainsOneItem() {
        final Skill skill = testData.skill();
        final ModelMap resultMap = new ModelMap();
        assertEquals("skills/list", skillsController.listSkills(resultMap));
        final List<Skill> skills = (List<Skill>) resultMap.get("skills");
        assertNotNull( skills );
        assertFalse(skills.isEmpty() ); 
        assertEquals( skill.getSkillId(), skills.get(0).getSkillId() );
        assertTrue(resultMap.get("newSkill") instanceof Skill);
        
    }
    
    @Test
    public void testAddSkill() {
        assertTrue( modelQueryHelper.allSkills().list().isEmpty() );
        final Skill c = new Skill();
        c.setDescription("some Description");
        c.setTitle("Some Title");
        assertEquals( "redirect:skills", skillsController.addSkill(c) );
        final List<Skill> skills = modelQueryHelper.allSkills().list();
        assertFalse( skills.isEmpty() );
        assertEquals( "some Description", skills.get(0).getDescription() );
        assertEquals( "Some Title", skills.get(0).getTitle() );
        assertNotNull( skills.get(0).getSkillId() );
        assertNotNull( skills.get(0).getCreationDate() );
        assertNotNull( skills.get(0).getRecordVersionNumber() );        
    }
}
