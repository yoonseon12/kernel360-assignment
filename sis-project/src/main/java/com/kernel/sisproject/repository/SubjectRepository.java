package com.kernel.sisproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kernel.sisproject.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
	boolean existsByName(String name);
}
