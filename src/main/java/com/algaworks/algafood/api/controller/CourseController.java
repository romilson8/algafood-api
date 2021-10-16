package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.auth.Course;
import com.algaworks.algafood.domain.model.auth.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Course> listar() {
        return courseRepository.findAll();
    }
}
