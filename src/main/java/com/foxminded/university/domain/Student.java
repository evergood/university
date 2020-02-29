package com.foxminded.university.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Student extends User {

    @Setter
    private List<Course> courses;
}
