package org.aryak.jobservice.repository;

import org.aryak.jobservice.domain.Job;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface JobRepository extends ReactiveMongoRepository<Job, String> {
}
