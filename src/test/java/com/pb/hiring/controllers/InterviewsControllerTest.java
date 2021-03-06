package com.pb.hiring.controllers;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.SessionFactory;
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
import com.pb.hiring.controllers.util.TestUserRequestContext;
import com.pb.hiring.model.Candidate;
import com.pb.hiring.model.Interview;
import com.pb.hiring.model.Job;
import com.pb.hiring.model.Skill;
import com.pb.hiring.model.User;
import com.pb.hiring.service.ModelQueryHelper;

@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml", "classpath:com/pb/hiring/unit-test-beans.xml"})
public class InterviewsControllerTest {
    @Autowired(required=true)
    private InterviewsController interviewsController;
    
    @Autowired(required=true)
    private ModelQueryHelper modelQueryHelper;
    
    @Autowired(required=true)
    private TestDataGenerator testData;
    
    @Autowired(required=true)
    private SessionFactory sessionFactory;
    
    @Autowired(required=true)
    private TestUserRequestContext testUserRequestContext;
    
    @BeforeClass
    public static void setUpLog4J() {
        BasicConfigurator.configure();
    }

    @Test
    public void testInterviewsControllerDependencies() {
        assertNotNull( interviewsController );
    }
    
    @Test
    public void testAddInterview() {
        final Candidate c = testData.candidate();
        final Job j = testData.job();
        c.addJob(j);
        final User u = testData.user();
        assertEquals("redirect:/app/candidates/"+c.getCandidateId()+"/jobs/"+j.getJobId(), 
                interviewsController.addInterview(c.getCandidateId(), j.getJobId(), u.getUserId(), new Date(), "Conference Room"));
        final List<Interview> interviews = modelQueryHelper.interviewsByCandidateAndJob(c.getCandidateId(), j.getJobId()).list();
        assertTrue ( !interviews.isEmpty() );
        final Interview i = interviews.get(0);
        assertEquals( j, i.getJob() );
        assertEquals( c, i.getCandidate() );
        assertEquals( u, i.getInterviewer() );
        assertNotNull( i.getStartTime() );
        assertEquals( "Conference Room", i.getLocation() );
    }
    
    @Test
    public void testGetInterviewNoSkills() {
        Job job = testData.jobNoSkills();
        final Interview i = testData.interview(job, testData.candidate(job), testData.user());
        final ModelMap modelMap = new ModelMap();
        assertEquals( "interviews/view", 
                interviewsController.getInterview(i.getCandidate().getCandidateId(), i.getJob().getJobId(), i.getInterviewId(), modelMap) );
        assertTrue( "model map contains an interview", modelMap.containsAttribute("interview") );
        assertTrue( "model map contains a list of skills", modelMap.containsAttribute("skills") );
        final Interview iFromMap = (Interview) modelMap.get("interview");
        assertEquals( "interviews are equal", i, iFromMap );
        final List<Skill> skills = (List<Skill>) modelMap.get("skills");
        assertTrue( "skills list is empty", skills.isEmpty() );
    }

    @Test
    public void testGetInterviewHaveSkills() {
        final Interview i = testData.interview();
        final ModelMap modelMap = new ModelMap();
        assertEquals( "interviews/view", 
                interviewsController.getInterview(i.getCandidate().getCandidateId(), i.getJob().getJobId(), i.getInterviewId(), modelMap) );
        assertTrue( "model map contains an interview", modelMap.containsAttribute("interview") );
        assertTrue( "model map contains a list of skills", modelMap.containsAttribute("skills") );
        final Interview iFromMap = (Interview) modelMap.get("interview");
        assertEquals( "interviews are equal", i, iFromMap );
        final List<Skill> skills = (List<Skill>) modelMap.get("skills");
        assertEquals( 1, skills.size() );
        assertTrue( "skills list contains the test skill", skills.contains(i.getJob().getSkills().get(0)) );
    }
    
    @Test
    public void testAddSkillToInterview() {
        final Interview i = testData.interview();
        final Skill s = testData.skill();
        assertFalse( i.getSkills().contains(s) );
        assertEquals( "redirect:/app/candidates/" + i.getCandidate().getCandidateId()+"/jobs/"+i.getJob().getJobId()+"/interviews/"+i.getInterviewId(), interviewsController.addSkillToInterview(i.getCandidate().getCandidateId(), i.getJob().getJobId(), i.getInterviewId(), s.getSkillId()));
        assertTrue( i.getSkills().contains(s) );
    }
    
    @Test
    public void testRemoveSkillFromInterview() {
        final Interview i = testData.interview();
        final Skill s = testData.skill();
        i.addSkill(s);
        assertFalse( i.getSkills().isEmpty() );
        assertEquals( "redirect:/app/candidates/" + i.getCandidate().getCandidateId()+"/jobs/"+i.getJob().getJobId()+"/interviews/"+i.getInterviewId(), interviewsController.removeSkillFromInterview(i.getCandidate().getCandidateId(), i.getJob().getJobId(), i.getInterviewId(), s.getSkillId(), true) );       
        assertTrue( "skill was removed", i.getSkills().isEmpty() );
    }
}
