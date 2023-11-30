package com.kernel.sisproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kernel.sisproject.dto.StudentSubjectRequest;
import com.kernel.sisproject.dto.StudentSubjectResponse;
import com.kernel.sisproject.entity.RequiredStatus;
import com.kernel.sisproject.entity.Student;
import com.kernel.sisproject.entity.Subject;
import com.kernel.sisproject.repository.StudentRepository;
import com.kernel.sisproject.repository.StudentSubjectRepository;
import com.kernel.sisproject.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentSubjectService {
	private final StudentRepository studentRepository;
	private final SubjectRepository subjectRepository;
	private final StudentSubjectRepository studentSubjectRepository;

	@Transactional
	public Long create(StudentSubjectRequest studentSubjectRequest, RequiredStatus requiredStatus, String lectureType) {
		validate(studentSubjectRequest);
		return studentSubjectRepository.save(
			StudentSubjectRequest.toEntity(
				findByStudent(studentSubjectRequest.getStudentId()),
				findBySubject(studentSubjectRequest.getSubjectId()),
				requiredStatus,
				lectureType
			)).getId();
	}

	public List<StudentSubjectResponse> findSubjectsByStudentId(Long studentId) {
		return studentSubjectRepository.findSubjectsByStudentId(studentId);
	}

	private void validate(final StudentSubjectRequest studentSubjectRequest) {
		// 필수과목을 포함한 수강신청한 과목 있는지 확인
		isDuplicateEnrolment(studentSubjectRequest.getStudentId(), studentSubjectRequest.getSubjectId());
	}

	private void isDuplicateEnrolment(final Long studentId, final Long subjectId) {
		if (studentSubjectRepository.existsByStudentIdAndSubjectId(studentId, subjectId))
			throw new IllegalArgumentException("해당 학생이 이미 수강신청한 과목임");
	}

	private Student findByStudent(final Long studentId) {
		return studentRepository.findById(studentId)
			.orElseThrow(() -> new IllegalArgumentException("학생 없음"));
	}

	private Subject findBySubject(final Long subjectId) {
		return subjectRepository.findById(subjectId)
			.orElseThrow(() -> new IllegalArgumentException("과목 없음"));
	}
}
