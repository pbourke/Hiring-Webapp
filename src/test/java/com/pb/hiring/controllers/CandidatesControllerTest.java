package com.pb.hiring.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.pb.hiring.model.Rating;
import com.pb.hiring.model.Skill;
import com.pb.hiring.model.User;
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
    
    @Test
    public void testGetCandidateJobPageNoRatings() {
        final Job j = testData.job();
        final Candidate c = testData.candidate(j);
        
        final ModelMap modelMap = new ModelMap();
        assertEquals( "candidates/job", candidatesController.getCandidateJobPage(c.getCandidateId(), j.getJobId(), modelMap) );
        assertTrue( "Ratings list is empty", ((List<Rating>)modelMap.get("ratings")).isEmpty() );
        assertEquals( "job was added to model map", j.getJobId(), ((Job)modelMap.get("job")).getJobId() );
    }
    
    @Test
    public void testGetCandidateJobPageOneRating() {
        final Job j = testData.job();
        final Candidate c = testData.candidate(j);
        final Skill s = j.getSkills().get(0);
        final User u = testData.user();
        final Rating r = testData.rating(j, c, s, u, 2);
        
        final ModelMap modelMap = new ModelMap();
        assertEquals( "candidates/job", candidatesController.getCandidateJobPage(c.getCandidateId(), j.getJobId(), modelMap) );
        assertEquals( "job was added to model map", j.getJobId(), ((Job)modelMap.get("job")).getJobId() );
        assertEquals( "Ratings list has 1 item", 1, ((List<Rating>)modelMap.get("ratings")).size() );
        assertEquals( "correct rating was returned", r, ((List<Rating>)modelMap.get("ratings")).get(0) );
    }
    
    @Test
    public void testGetCandidateJobPageOneInterview() {
        final Job j = testData.job();
        final Candidate c = testData.candidate(j);
        final User interviewer = testData.user();
        final User nonInterviewer = testData.user();
        final Interview i = testData.interview(j, c, interviewer);
        final ModelMap modelMap = new ModelMap();
        assertEquals("candidates/job", candidatesController.getCandidateJobPage(c.getCandidateId(), j.getJobId(), modelMap) );
        assertEquals( "job was added to model map", j.getJobId(), ((Job)modelMap.get("job")).getJobId() );
        assertTrue( "interviews was added to model map", modelMap.containsAttribute("interviews") );
        assertEquals( "interviews size was correct", 1, ((List<Interview>)modelMap.get("interviews")).size() );
        assertEquals( "interviews was set correctly", i, ((List<Interview>)modelMap.get("interviews")).get(0) );        
        assertTrue( "users was added to model map", modelMap.containsAttribute("users") );
        final List<User> users = (List<User>) modelMap.get("users");
        assertNotNull( "users was not null", users );
        assertEquals( "users size was correct", 2, users.size() );
        assertTrue( "users contains non-interviewer", users.contains(nonInterviewer));
    }
    
    @Test
    public void testAddRating() {
        final User u = testData.user();
        testUserRequestContext.setUserEmail( u.getEmail() );

        final Job j = testData.job();
        final Candidate c = testData.candidate(j);
        final Skill s = j.getSkills().get(0);
        
        assertTrue("No ratings yet for this candidate and job", 
                modelQueryHelper.ratingsByCandidateAndJob(c.getCandidateId(), j.getJobId()).list().isEmpty());

        assertEquals( "redirect:/app/candidates/"+c.getCandidateId()+"/jobs/"+j.getJobId(), 
                candidatesController.addRating(c.getCandidateId(), j.getJobId(), s.getSkillId(), 3, "some notes"));
        
        // todo: query for ratings and verify that one has been added
        
        final List<Rating> ratings = (List<Rating>) modelQueryHelper.ratingsByCandidateAndJob(c.getCandidateId(), j.getJobId()).list();
        assertEquals("ratings list size correct", 1, ratings.size());
        final Rating r = ratings.get(0);
        assertEquals("Job was assigned", j, r.getJob());
        assertEquals("Candidate was assigned", c, r.getCandidate());
        assertEquals("Skill was assigned", s, r.getSkill());
        assertEquals("Notes were assigned", "some notes", r.getNotes());
        assertEquals("rating value was assigned", 3, r.getRating());
        assertNotNull("creationDate is null", r.getCreationDate());
        assertEquals("User was assigned", u.getEmail(), r.getUser().getEmail());
    }
    
    @Test
    public void testAddRatingFollowedByAddJob() {
        final User u = testData.user();
        testUserRequestContext.setUserEmail( u.getEmail() );

        final Job j1 = testData.job();
        final Candidate c = testData.candidate(j1);
        candidatesController.addRating(c.getCandidateId(), j1.getJobId(), j1.getSkills().get(0).getSkillId(), 2, "test");
        
        final Job j2 = testData.job();
        candidatesController.assignJob(c.getCandidateId(), j2.getJobId(), new ModelMap());
        
        sessionFactory.getCurrentSession().flush();
    }
}
