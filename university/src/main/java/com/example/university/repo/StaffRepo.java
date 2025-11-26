package com.example.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.university.domain.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

}
