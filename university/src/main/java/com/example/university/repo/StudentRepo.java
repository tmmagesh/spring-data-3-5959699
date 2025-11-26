package com.example.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.university.domain.Student;

public interface StudentRepo extends JpaRepository <Student, Integer> {
  
}
