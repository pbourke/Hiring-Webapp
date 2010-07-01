package com.pb.hiring.service;

import org.hibernate.Criteria;

/**
 * Provides helper methods for constructing queries against model objects.
 * 
 * @author pbourke
 */
public interface ModelQueryHelper {
    Criteria allCandidates();
    Criteria candidateById(final Long candidateId);
    
    Criteria allJobs();
    Criteria jobById(final Long jobId);
}
