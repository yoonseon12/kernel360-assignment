package com.kernel.sisproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "subject")
public class Subject extends BaseEntity {
	private String name;

	@Builder
	public Subject(String name) {
		this.name = name;
	}
}

