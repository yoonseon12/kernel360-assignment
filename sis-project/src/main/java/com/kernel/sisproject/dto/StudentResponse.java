package com.kernel.sisproject.dto;

import com.kernel.sisproject.entity.Student;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentResponse {
	private Long id;
	private String name;

	public static StudentResponse of(Student student) {
		return StudentResponse.builder()
			.id(student.getId())
			.name(student.getName())
			.build();
	}
}
