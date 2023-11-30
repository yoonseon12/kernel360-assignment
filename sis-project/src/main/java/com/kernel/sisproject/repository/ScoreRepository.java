package com.kernel.sisproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kernel.sisproject.dto.GradeResponse;
import com.kernel.sisproject.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
	boolean existsByStudentIdAndSubjectIdAndPointIsNotNull(Long studentId, Long subjectId);

	@Query("select new com.kernel.sisproject.dto.GradeResponse("
		+ "stu.name, stu.id, "
		+ "(select subject.name from StudentSubject ss inner join Subject subject on ss.subject.id = subject.id where ss.requiredStatus = 'REQUIRED' and ss.student.id = sco.student.id), "
		+ "(select ss.lectureType from StudentSubject ss inner join Subject subject on ss.subject.id = subject.id where ss.student.id = sco.student.id and ss.subject.id = :subjectId), "
		+ "sub.name, sco.point)"
		+ "  from Score sco"
		+ " inner join Student stu"
		+ "    on sco.student.id = stu.id"
		+ " inner join Subject sub"
		+ "    on sco.subject.id = sub.id"
		+ " where sco.subject.id = :subjectId")
	List<GradeResponse> findAllBySubject(@Param("subjectId") Long subjectId);

}
