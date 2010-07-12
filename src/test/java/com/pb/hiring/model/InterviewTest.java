package com.pb.hiring.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class InterviewTest {
    @Test(expected=IllegalArgumentException.class)
    public void testNullJobInConstructor() {
        new Interview(null, new Candidate(), new User(), null, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullCandidateInConstructor() {
        new Interview(new Job(), null, new User(), null, null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNullInterviewerInConstructor() {
        new Interview(new Job(), new Candidate(), null, null, null);
    }
    
    @Test
    public void testConstructorAssignsAllAttributes() {
        final Job job = new Job();
        final Candidate candidate = new Candidate();
        final User interviewer = new User();
        final Date startTime = new Date();
        final String location = "Conference Room";
        final Interview i = new Interview(job, candidate, interviewer, startTime, location);
        assertEquals(job, i.getJob());
        assertEquals(candidate, i.getCandidate());
        assertEquals(interviewer, i.getInterviewer());
        assertEquals(startTime, i.getStartTime());
        assertEquals(location, i.getLocation());
    }
}
