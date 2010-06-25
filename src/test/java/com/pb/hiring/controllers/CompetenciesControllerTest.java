package com.pb.hiring.controllers;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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

import com.pb.hiring.model.Competency;

@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml", "classpath:com/pb/hiring/unit-test-beans.xml"})
public class CompetenciesControllerTest {
    @Autowired(required=true)
    private CompetenciesController competenciesController;
    
    @BeforeClass
    public static void setUpLog4J() {
        BasicConfigurator.configure();
    }
    
    @Test
    public void testEmptyCompetenciesList() {
        final ModelMap resultMap = new ModelMap();
        assertEquals("competencies/list", competenciesController.listCompetencies(resultMap));
        final List<Competency> competencies = (List<Competency>) resultMap.get("competencies");
        assertNotNull( competencies );
        assertTrue(competencies.isEmpty() );
        assertTrue(resultMap.get("newCompetency") instanceof Competency);
    }
    
    @Test
    public void testAddCompetency() {
        assertTrue( listCompetencies().isEmpty() );
        final Competency c = new Competency();
        c.setDescription("some Description");
        c.setTitle("Some Title");
        assertEquals( "redirect:competencies", competenciesController.addCompetency(c) );
        final List<Competency> competencies = listCompetencies();
        assertFalse( competencies.isEmpty() );
        assertEquals( "some Description", competencies.get(0).getDescription() );
        assertEquals( "Some Title", competencies.get(0).getTitle() );
        assertNotNull( competencies.get(0).getCompetencyId() );
        assertNotNull( competencies.get(0).getCreationDate() );
        assertNotNull( competencies.get(0).getRecordVersionNumber() );        
    }
    
    private List<Competency> listCompetencies() {
        final ModelMap resultMap = new ModelMap();
        competenciesController.listCompetencies(resultMap);
        return (List<Competency>) resultMap.get("competencies");
    }
}
