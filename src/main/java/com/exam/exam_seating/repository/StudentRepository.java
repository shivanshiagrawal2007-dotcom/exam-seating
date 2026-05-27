package com.exam.exam_seating.repository;

import com.exam.exam_seating.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByRollNoContainingIgnoreCase(String rollNo);
}
