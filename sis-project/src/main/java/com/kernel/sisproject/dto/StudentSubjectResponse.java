package com.kernel.sisproject.dto;

import lombok.Getter;

@Getter
public class StudentSubjectResponse {
	private Long subjectId;
	private String subjectName;

	public StudentSubjectResponse(Long subjectId, String subjectName) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}
}
