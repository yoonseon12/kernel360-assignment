package com.kernel.sisproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kernel.sisproject.dto.ScoreRequest;
import com.kernel.sisproject.entity.Score;
import com.kernel.sisproject.entity.Student;
import com.kernel.sisproject.entity.Subject;
import com.kernel.sisproject.repository.ScoreRepository;
import com.kernel.sisproject.repository.StudentRepository;
import com.kernel.sisproject.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScoreService {
	private final ScoreRepository scoreRepository;
	private final SubjectRepository subjectRepository;
	private final StudentRepository studentRepository;

	@Transactional
	public Long create(ScoreRequest scoreRequest) {
		validate(scoreRequest);
		Student findStudent = findByStudent(scoreRequest.getStudentId());
		Subject findSubject = findBySubject(scoreRequest.getSubjectId());
		Score savedScore = scoreRepository.save(ScoreRequest.toEntity(scoreRequest, findStudent, findSubject));
		return savedScore.getId();
	}

	private void validate(ScoreRequest scoreRequest) {
		isDuplicateScore(scoreRequest.getStudentId(), scoreRequest.getSubjectId());
	}

	private void isDuplicateScore(Long studentId, Long subjectId) {
		if (scoreRepository.existsByStudentIdAndSubjectIdAndPointIsNotNull(studentId, subjectId))
			throw new IllegalArgumentException("이미 점수가 등록된 과목");
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
