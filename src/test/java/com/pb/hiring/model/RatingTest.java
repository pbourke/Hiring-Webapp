package com.pb.hiring.model;

import org.junit.Test;

public class RatingTest {
    @Test(expected=IllegalArgumentException.class)
    public void testNullJobInConstructor() {
        new Rating(null, new Skill(), new Candidate(), new User());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNullSkillInConstructor() {
        new Rating(new Job(), null, new Candidate(), new User());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullCandidateInConstructor() {
        new Rating(new Job(), new Skill(), null, new User());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNullUserInConstructor() {
        new Rating(new Job(), new Skill(), new Candidate(), null);
    }
}
