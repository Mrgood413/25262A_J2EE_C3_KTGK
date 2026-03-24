package com.example.KTGK.repository;

import com.example.KTGK.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Override
    @EntityGraph(attributePaths = "department")
    Page<Doctor> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "department")
    Optional<Doctor> findById(Long id);

    @EntityGraph(attributePaths = "department")
    Page<Doctor> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
