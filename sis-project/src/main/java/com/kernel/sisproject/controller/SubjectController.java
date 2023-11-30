package com.kernel.sisproject.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kernel.sisproject.dto.SubjectRequest;
import com.kernel.sisproject.dto.SubjectResponse;
import com.kernel.sisproject.service.SubjectService;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SubjectController {
	private final SubjectService subjectService;

	@PostMapping("/api/v1/subjects")
	public ResponseEntity<Long> create(@RequestBody SubjectRequest subjectRequest) {
		Long savedSubjectId = subjectService.create(subjectRequest);
		URI uri = URI.create("/api/v1/subjects/" + savedSubjectId);
		return ResponseEntity.created(uri).body(savedSubjectId);
	}

	@GetMapping("/api/v1/subjects")
	public ResponseEntity<SubjectListResponse> findAll() {
		List<SubjectResponse> subjects = subjectService.findAll();
		return ResponseEntity.ok().body(SubjectListResponse.of(subjects));
	}

	@Getter
	@Builder
	static class SubjectListResponse {
		private List<SubjectResponse> subjects;
		public static SubjectListResponse of(List<SubjectResponse> subjects) {
			return SubjectListResponse.builder()
				.subjects(subjects)
				.build();
		}
	}
}
