package com.example.university.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.university.domain.Course;
public interface CourseRepo extends JpaRepository <Course, Integer>{
  
}
