package com.kernel.sisproject.dto;

import com.kernel.sisproject.entity.RequiredStatus;
import com.kernel.sisproject.entity.Subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class SubjectRequest {
	private String name;

	public static Subject toEntity(SubjectRequest subjectRequest) {
		return Subject.builder()
			.name(subjectRequest.name)
			.build();
	}
}
