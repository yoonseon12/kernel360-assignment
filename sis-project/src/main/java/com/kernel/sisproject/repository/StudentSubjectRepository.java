package com.kernel.sisproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kernel.sisproject.dto.StudentSubjectResponse;
import com.kernel.sisproject.dto.SubjectResponse;
import com.kernel.sisproject.entity.StudentSubject;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {
	boolean existsByStudentIdAndSubjectId(Long studentId, Long subjectId);
	@Query("select new com.kernel.sisproject.dto.StudentSubjectResponse("
		+  "sb.id, sb.name"
		+  ")"
		 + "  from StudentSubject stsb"
		 + " inner join Subject  sb"
		 + " on stsb.subject.id = sb.id"
		 + " where stsb.student.id = :studentId")
	List<StudentSubjectResponse> findSubjectsByStudentId(Long studentId);
}
