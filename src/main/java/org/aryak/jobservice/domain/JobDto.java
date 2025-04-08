package org.aryak.jobservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class JobDto {

    private String company;
    private String role;
    private String description;
    private Set<String> skills;
    private double salary;

}
