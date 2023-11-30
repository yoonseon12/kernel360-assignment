package com.kernel.sisproject.dto;

import com.kernel.sisproject.entity.School;
import com.kernel.sisproject.entity.Student;
import com.kernel.sisproject.entity.Subject;

import lombok.Getter;

@Getter
public class StudentRequest {
	private String name;
	private Long subjectId;

	public static Student toEntity(StudentRequest studentRequest, School school) {
		return Student.builder()
			.name(studentRequest.name)
			.school(school)
			.build();
	}
}
