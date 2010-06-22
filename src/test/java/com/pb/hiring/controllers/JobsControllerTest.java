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
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml"})
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
        final List<Job> jobsList = (List<Job>) modelMap.get("jobs");
        assertNotNull( jobsList );
        assertEquals(0, jobsList.size());
    }
}