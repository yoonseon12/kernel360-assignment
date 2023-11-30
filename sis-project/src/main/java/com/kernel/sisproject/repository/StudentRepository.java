package com.kernel.sisproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kernel.sisproject.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	boolean existsByName(String name);
}
