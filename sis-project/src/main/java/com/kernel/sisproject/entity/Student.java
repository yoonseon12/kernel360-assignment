package com.kernel.sisproject.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student")
public class Student extends BaseEntity {
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id", nullable = false)
	private School school;

	@OneToMany(mappedBy = "student")
	private List<StudentSubject> studentSubjects = new ArrayList<>();

	@Builder
	public Student(String name, School school, List<StudentSubject> studentSubjects) {
		this.name = name;
		this.school = school;
		this.studentSubjects = studentSubjects;
	}
}
