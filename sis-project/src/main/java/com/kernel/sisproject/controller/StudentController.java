package com.kernel.sisproject.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kernel.sisproject.dto.ScoreRequest;
import com.kernel.sisproject.dto.StudentRequest;
import com.kernel.sisproject.dto.StudentResponse;
import com.kernel.sisproject.dto.StudentSubjectRequest;
import com.kernel.sisproject.dto.StudentSubjectResponse;
import com.kernel.sisproject.dto.SubjectResponse;
import com.kernel.sisproject.entity.Student;
import com.kernel.sisproject.service.StudentService;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;

	@PostMapping("/api/v1/students")
	public ResponseEntity<Long> create(@RequestBody StudentRequest StudentRequest) {
		Long savedStudentId = studentService.create(StudentRequest);
		URI uri = URI.create("/api/v1/students/" + savedStudentId);
		return ResponseEntity.created(uri).body(savedStudentId);
	}

	@GetMapping("/api/v1/students")
	public ResponseEntity<StudentListResponse> findAll() {
		List<StudentResponse> Students = studentService.findAll();
		return ResponseEntity.ok().body(StudentListResponse.of(Students));
	}

	@GetMapping("/api/v1/students/{studentId}/subjects")
	public ResponseEntity<EnrolmentListResponse> findSubjectsByStudent(@PathVariable("studentId") Long studentId) {
		List<StudentSubjectResponse> subjects = studentService.findSubjectsByStudent(studentId);
		return ResponseEntity.ok().body(EnrolmentListResponse.of(subjects));
	}

	@PostMapping("/api/v1/students/{studentId}/subjects")
	public ResponseEntity<Long> createSubjectByStudent(@RequestBody StudentSubjectRequest studentSubjectRequest) {
		Long savedStudentId = studentService.createSubjectByStudent(studentSubjectRequest);
		URI uri = URI.create("/api/v1/students/" + savedStudentId
			+ "/subjects/" + savedStudentId);
		return ResponseEntity.created(uri).body(savedStudentId);
	}

	@PostMapping("/api/v1/students/{studentId}/subjects/{subjectId}/scores")
	public ResponseEntity<Long> createScoreBySubjectByStudent(@RequestBody ScoreRequest scoreRequest) {
		Long savedScoreId = studentService.createScoreBySubjectByStudent(scoreRequest);
		URI uri = URI.create("/api/v1/students/" + scoreRequest.getStudentId()
			+ "/subjects/" + scoreRequest.getSubjectId()
			+ "/scores/" + savedScoreId);
		return ResponseEntity.created(uri).body(savedScoreId);
	}

	@Getter
	@Builder
	static class StudentListResponse {
		private List<StudentResponse> students;
		public static StudentListResponse of(List<StudentResponse> Students) {
			return StudentListResponse.builder()
				.students(Students)
				.build();
		}
	}

	@Getter
	@Builder
	static class EnrolmentListResponse {
		private List<StudentSubjectResponse> subjects;
		public static EnrolmentListResponse of(List<StudentSubjectResponse> subjects) {
			return EnrolmentListResponse.builder()
				.subjects(subjects)
				.build();
		}
	}
}