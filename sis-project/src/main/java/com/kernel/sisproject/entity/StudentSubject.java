package com.kernel.sisproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student_subject")
public class StudentSubject extends BaseEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@Enumerated(EnumType.STRING)
	@Column(name = "required_status")
	private RequiredStatus requiredStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "lecture_type")
	private LectureType lectureType;

	@Builder
	public StudentSubject(Student student, Subject subject, RequiredStatus requiredStatus, LectureType lectureType) {
		this.student = student;
		this.subject = subject;
		this.requiredStatus = requiredStatus;
		this.lectureType = lectureType;
	}
}
