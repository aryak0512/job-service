package org.aryak.jobservice.service;

import org.aryak.jobservice.domain.JobDto;
import org.aryak.jobservice.repository.JobRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Flux<JobDto> findAll() {

        return jobRepository.findAll()
                .map(job -> {
                    JobDto dto = new JobDto();
                    BeanUtils.copyProperties(job, dto);
                    return dto;
                });
    }
}
