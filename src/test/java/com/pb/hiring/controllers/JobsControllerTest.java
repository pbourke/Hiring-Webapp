package com.pb.hiring.controllers;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
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

import com.pb.hiring.model.Job;

@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml", "classpath:com/pb/hiring/unit-test-beans.xml"})
public class JobsControllerTest {
    @Autowired(required=true)
    private JobsController jobsController;
    
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
        final ModelMap modelMapFromAdd = new ModelMap();
        assertEquals("jobs/view", jobsController.addJob(job, modelMapFromAdd));
        assertTrue( modelMapFromAdd.containsAttribute("job") );
        
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
        final ModelMap modelMap = new ModelMap();
        
        final Job job1 = new Job();
        job1.setTitle("Developer");
        jobsController.addJob(job1, modelMap);
        
        Thread.sleep(100L);
        
        final Job job2 = new Job();
        job2.setTitle("Manager");
        jobsController.addJob(job2, modelMap);
        
        jobsController.listJobs(modelMap);
        final List<Job> jobsList = (List<Job>) modelMap.get("jobs");
 
        assertEquals(2, jobsList.size());
        assertEquals("Developer", jobsList.get(1).getTitle());
        assertEquals("Manager", jobsList.get(0).getTitle());
    }
    
    @Test
    public void testGetJobJobExists() {
        final Job job = new Job();
        job.setTitle("Some Job");
        job.setDescription("Work Work Work");
        jobsController.addJob(job, new ModelMap());
        assertNotNull(job.getJobId());
        
        final ModelMap modelMap = new ModelMap();
        assertEquals("jobs/view", jobsController.getJob(job.getJobId(), modelMap));
        
        assertTrue( modelMap.containsAttribute("job") );
        assertEquals( job.getJobId(), ((Job)modelMap.get("job")).getJobId() );
    }
}
