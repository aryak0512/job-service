package org.aryak.jobservice.controller;

import org.aryak.jobservice.domain.JobDto;
import org.aryak.jobservice.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/all")
    public Flux<JobDto> getAllJobs(){
        return jobService.findAll();
    }
}
