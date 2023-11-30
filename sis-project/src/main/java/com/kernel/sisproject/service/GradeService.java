package com.kernel.sisproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kernel.sisproject.dto.GradeResponse;
import com.kernel.sisproject.entity.LectureType;
import com.kernel.sisproject.repository.ScoreRepository;
import com.kernel.sisproject.strategy.BasicEvaluation;
import com.kernel.sisproject.strategy.GradeEvaluation;
import com.kernel.sisproject.strategy.MajorEvaluation;
import com.kernel.sisproject.strategy.PassFailEvaluation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeService {
	private final ScoreRepository scoreRepository;
	public List<GradeResponse> findAllBySubject(Long subjectId) {
		List<GradeResponse> grades = scoreRepository.findAllBySubject(subjectId);
		return grades.stream()
			.peek(g -> {
				g.setGrade(getGradeEvaluation(g).getGrade(g.getPoint()));
			})
			.collect(Collectors.toList());
	}

	private GradeEvaluation getGradeEvaluation(GradeResponse gradeResponse) {
		if (gradeResponse.getRequiredSubject().equals(gradeResponse.getSubject())) return new MajorEvaluation();
		if (gradeResponse.getLectureType() == LectureType.PASS_FAIL) return new PassFailEvaluation();
		else return new BasicEvaluation();
	}
}
