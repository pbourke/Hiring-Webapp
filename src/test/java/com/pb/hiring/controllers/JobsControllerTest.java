package com.pb.hiring.controllers;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
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
import com.pb.hiring.model.Job;
import com.pb.hiring.model.Skill;

@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml", "classpath:com/pb/hiring/unit-test-beans.xml"})
public class JobsControllerTest {
    @Autowired(required=true)
    private JobsController jobsController;
    
    @Autowired(required=true)
    private TestDataGenerator testData;
    
    @BeforeClass
    public static void setUpLog4J() {
        BasicConfigurator.configure();
    }
    
    @Test
    public void testEmptyJobsList() {
        final ModelMap modelMap = new ModelMap();
        assertEquals("jobs/list", jobsController.listJobs(modelMap));
        assertTrue(modelMap.containsAttribute("jobs"));
        assertTrue(modelMap.containsAttribute("newJob"));
        assertTrue( modelMap.get("newJob") instanceof Job );
        final List<Job> jobsList = (List<Job>) modelMap.get("jobs");
        assertNotNull( jobsList );
        assertEquals(0, jobsList.size());
    }
    
    @Test
    public void testAddJob() {
        final Job job = new Job();
        job.setTitle("Some Job");
        job.setDescription("Work Work Work");
        assertTrue("addJob redirects to newly-created Job detail page", 
                jobsController.addJob(job).startsWith("redirect:jobs/"));
        
        final ModelMap modelMapFromList = new ModelMap();
        assertEquals("jobs/list", jobsController.listJobs(modelMapFromList));
        assertTrue(modelMapFromList.containsAttribute("jobs"));
        final List<Job> jobsList = (List<Job>) modelMapFromList.get("jobs");
        assertNotNull( jobsList );
        assertEquals( 1, jobsList.size() );
        assertNotNull( jobsList.get(0).getJobId() );
        assertEquals( "Some Job", jobsList.get(0).getTitle() );
        assertNotNull( jobsList.get(0).getCreationDate() );
        assertNotNull( jobsList.get(0).getRecordVersionNumber() );
    }
    
    @Test
    public void testAddTwoJobs() throws InterruptedException {        
        final Job job1 = testData.job();
        
        Thread.sleep(100L);
        
        final Job job2 = testData.job();
        
        final ModelMap modelMap = new ModelMap();
        jobsController.listJobs(modelMap);
        final List<Job> jobsList = (List<Job>) modelMap.get("jobs");
 
        assertEquals(2, jobsList.size());
        assertEquals(job1.getTitle(), jobsList.get(1).getTitle());
        assertEquals(job2.getTitle(), jobsList.get(0).getTitle());
    }
    
    @Test
    public void testGetJobJobExists() {
        final Job job = testData.job();
        
        final ModelMap modelMap = new ModelMap();
        assertEquals("jobs/view", jobsController.getJob(job.getJobId(), modelMap));
        
        assertTrue( modelMap.containsAttribute("job") );
        assertEquals( job.getJobId(), ((Job)modelMap.get("job")).getJobId() );
    }
    
    @Test
    public void testAddSkill() {
        final Job job = testData.job();
        final Skill skill = testData.skill();
        
        final ModelMap modelMap = new ModelMap();
        jobsController.addSkill(job.getJobId(), skill.getSkillId(), modelMap);
        assertTrue( modelMap.containsAttribute("job") );
        final Job jobFromMap = (Job) modelMap.get("job");
        assertEquals( job.getJobId(), jobFromMap.getJobId() );
        assertEquals( 2, jobFromMap.getSkills().size() );
        assertTrue( jobFromMap.getSkills().contains(skill) );
    }
    
    @Test
    public void testRemoveSkill() {
        final Job job = testData.job();
        final Skill skill = job.getSkills().get(0);
        final ModelMap modelMap = new ModelMap();
        
        assertFalse( "job has a skill", job.getSkills().isEmpty() );
        assertEquals( "redirect:/app/jobs/" + job.getJobId(), jobsController.removeSkill(job.getJobId(), skill.getSkillId(), true, modelMap) );
        final Job jobAfterRemove = (Job) modelMap.get("job");
        assertEquals( "job is the same", job.getJobId(), jobAfterRemove.getJobId() );
        assertEquals( 0, jobAfterRemove.getSkills().size() );        
    }
}
