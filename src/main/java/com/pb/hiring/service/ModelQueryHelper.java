package com.pb.hiring.service;

import org.hibernate.Criteria;

/**
 * Provides helper methods for constructing queries against model objects.
 * 
 * @author pbourke
 */
public interface ModelQueryHelper {
    Criteria allCandidates();
    Criteria candidateById(Long candidateId);
    
    Criteria allJobs();
    Criteria jobById(Long jobId);
    
    Criteria allSkills();
    Criteria skillById(Long skillId);
    
    Criteria ratingsByCandidateAndJob(Long candidateId, Long jobId);
    
    Criteria allUsers();
    Criteria userByEmail(String email);
    Criteria userById(Long userId);
    
    Criteria interviewsByCandidateAndJob(Long candidateId, Long jobId);
}
