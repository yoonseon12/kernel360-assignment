package com.kernel.sisproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kernel.sisproject.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
	Optional<School> findByName(String name);
}
