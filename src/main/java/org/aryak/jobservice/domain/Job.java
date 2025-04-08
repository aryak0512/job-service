package org.aryak.jobservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Document
public class Job {

    @Id
    private String id;
    private String company;
    private String role;
    private String description;
    private Set<String> skills;
    private double salary;

}
