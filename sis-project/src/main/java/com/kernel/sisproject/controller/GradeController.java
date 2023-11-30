package com.kernel.sisproject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kernel.sisproject.dto.GradeResponse;
import com.kernel.sisproject.service.GradeService;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GradeController {
	private final GradeService gradeService;

	@GetMapping("/api/v1/grades/{subjectId}")
	public ResponseEntity<GradeListResponse> findAllBySubject(@PathVariable("subjectId") Long subjectId) {
		List<GradeResponse> grades = gradeService.findAllBySubject(subjectId);
		return ResponseEntity.ok().body(GradeListResponse.of(grades));
	}

	@Getter
	@Builder
	static class GradeListResponse {
		private List<GradeResponse> grades;

		public static GradeListResponse of(List<GradeResponse> grades) {
			return GradeListResponse.builder()
				.grades(grades)
				.build();
		}
	}
}
