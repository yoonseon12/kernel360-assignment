package com.kernel.sisproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kernel.sisproject.dto.ScoreRequest;
import com.kernel.sisproject.dto.StudentRequest;
import com.kernel.sisproject.dto.StudentResponse;
import com.kernel.sisproject.dto.StudentSubjectRequest;
import com.kernel.sisproject.dto.StudentSubjectResponse;
import com.kernel.sisproject.entity.RequiredStatus;
import com.kernel.sisproject.entity.School;
import com.kernel.sisproject.entity.Student;
import com.kernel.sisproject.repository.SchoolRepository;
import com.kernel.sisproject.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {
	private final String SCHOOL_NAME = "SilverBell School";
	private final StudentSubjectService studentSubjectService;
	private final ScoreService scoreService;
	private final StudentRepository studentRepository;
	private final SchoolRepository schoolRepository;

	@Transactional
	public Long create(StudentRequest studentRequest) {
		School findSchool = findBySchool(SCHOOL_NAME);
		validate(studentRequest);
		Student savedStudent = studentRepository.save(
			studentRequest.toEntity(studentRequest, findSchool));
		studentSubjectService.create(
			new StudentSubjectRequest(savedStudent.getId(), studentRequest.getSubjectId()),
			RequiredStatus.REQUIRED, null);
		return savedStudent.getId();
	}

	private void validate(StudentRequest studentRequest) {
		isDuplicateStudentName(studentRequest.getName());
	}

	private void isDuplicateStudentName(String name) {
		if (studentRepository.existsByName(name))
			throw new IllegalArgumentException("이미 등록된 학생명");
	}

	private School findBySchool(String schoolName) {
		return schoolRepository.findByName(schoolName)
			.orElseThrow(() -> new IllegalArgumentException("학교 없음"));
	}

	public List<StudentResponse> findAll() {
		return studentRepository.findAll().stream()
			.map(StudentResponse::of)
			.toList();
	}

	@Transactional
	public Long createSubjectByStudent(StudentSubjectRequest studentSubjectRequest) {
		return studentSubjectService.create(studentSubjectRequest, RequiredStatus.OPTIONAL, studentSubjectRequest.getLectureType());
	}

	public List<StudentSubjectResponse> findSubjectsByStudent(Long studentId) {
		return studentSubjectService.findSubjectsByStudentId(studentId);
	}

	@Transactional
	public Long createScoreBySubjectByStudent(ScoreRequest scoreRequest) {
		return scoreService.create(scoreRequest);
	}
}
