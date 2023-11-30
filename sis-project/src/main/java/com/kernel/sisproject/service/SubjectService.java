package com.kernel.sisproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kernel.sisproject.dto.SubjectRequest;
import com.kernel.sisproject.dto.SubjectResponse;
import com.kernel.sisproject.entity.Subject;
import com.kernel.sisproject.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectService {
	private final SubjectRepository subjectRepository;

	@Transactional
	public Long create(SubjectRequest subjectRequest) {
		validate(subjectRequest);
		Subject savedSubject = subjectRepository.save(SubjectRequest.toEntity(subjectRequest));
		return savedSubject.getId();
	}

	private void validate(SubjectRequest subjectRequest) {
		isDuplicateSubjectName(subjectRequest.getName());
	}

	private void isDuplicateSubjectName(String name) {
		if (subjectRepository.existsByName(name))
			throw new IllegalArgumentException("이미 등록된 과목명");
	}

	public List<SubjectResponse> findAll() {
		return subjectRepository.findAll().stream()
			.map(SubjectResponse::of)
			.toList();
	}
}
