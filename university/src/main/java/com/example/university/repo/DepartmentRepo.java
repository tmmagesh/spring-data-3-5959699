package com.example.university.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.university.domain.Department;
import com.example.university.domain.Staff;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{
  
}


