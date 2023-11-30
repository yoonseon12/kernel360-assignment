package com.kernel.sisproject.dto;

import com.kernel.sisproject.entity.Subject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubjectResponse {
	private Long id;
	private String name;

	public static SubjectResponse of(Subject subject) {
		return SubjectResponse.builder()
			.id(subject.getId())
			.name(subject.getName())
			.build();
	}
}
