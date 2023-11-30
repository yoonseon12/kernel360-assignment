package com.kernel.sisproject.dto;

import com.kernel.sisproject.entity.Score;
import com.kernel.sisproject.entity.Student;
import com.kernel.sisproject.entity.Subject;

import lombok.Getter;

@Getter
public class ScoreRequest {
	private Long studentId;
	private Long subjectId;
	private Integer point;

	public static Score toEntity(ScoreRequest scoreRequest, Student student, Subject subject) {
		return Score.builder()
			.student(student)
			.subject(subject)
			.point(scoreRequest.point)
			.build();
	}
}
