package com.kernel.sisproject.dto;

import com.kernel.sisproject.entity.LectureType;

import lombok.Getter;

@Getter
public class GradeResponse {
	private String name;
	private Long studentId;
	private String requiredSubject;
	private LectureType lectureType;
	private String subject;
	private int point;
	private String grade;

	public GradeResponse(String name, Long studentId, String requiredSubject, LectureType lectureType, String subject, int point) {
		this.name = name;
		this.studentId = studentId;
		this.requiredSubject = requiredSubject;
		this.lectureType = lectureType;
		this.subject = subject;
		this.point = point;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
