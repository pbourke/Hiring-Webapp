package com.pb.hiring.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import com.pb.hiring.model.Candidate;
import com.pb.hiring.service.ModelQueryHelper;

@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml", "classpath:com/pb/hiring/unit-test-beans.xml"})
public class CandidatesControllerTest {
    @Autowired(required=true)
    private CandidatesController candidatesController;
    
    @Autowired(required=true)
    private ModelQueryHelper modelQueryHelper;
    
    @BeforeClass
    public static void setUpLog4J() {
        BasicConfigurator.configure();
    }
    
    @Test
    public void testListCandidatesEmpty() {
        final ModelMap map = new ModelMap();
        assertEquals("getCandidates returns correct view page", "candidates/list", candidatesController.listCandidates(map));
        assertTrue( map.containsAttribute("candidates") );
        assertTrue( "candidate list is empty", ((List<Candidate>)map.get("candidates")).isEmpty() );
    }
    
    @Test
    public void testAddCandidate() {
        final Candidate c = new Candidate();
        c.setEmail("some@email.com");
        c.setName("Some Name");
        assertEquals( "redirect:/app/candidates", candidatesController.addCandidate(c) );
        final List<Candidate> candidates = modelQueryHelper.allCandidates().list();
        assertFalse( "candidates list is not empty", candidates.isEmpty() );
        final Candidate candidate = candidates.get(0);
        assertEquals( "email is correct", c.getEmail(), candidate.getEmail() );
        assertEquals( "name is correct", c.getName(), candidate.getName() );
    }
}
