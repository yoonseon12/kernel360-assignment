package com.kernel.sisproject.dto;

import com.kernel.sisproject.entity.LectureType;
import com.kernel.sisproject.entity.RequiredStatus;
import com.kernel.sisproject.entity.Student;
import com.kernel.sisproject.entity.StudentSubject;
import com.kernel.sisproject.entity.Subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubjectRequest {
	private Long studentId;
	private Long subjectId;
	private String lectureType;

	public StudentSubjectRequest(Long studentId, Long subjectId) {
		this.studentId = studentId;
		this.subjectId = subjectId;
	}

	public static StudentSubject toEntity(Student student, Subject subject, RequiredStatus requiredStatus, String lectureType) {
		return StudentSubject.builder()
			.student(student)
			.subject(subject)
			.requiredStatus(requiredStatus)
			.lectureType(LectureType.getLectureType(lectureType))
			.build();
	}
}
