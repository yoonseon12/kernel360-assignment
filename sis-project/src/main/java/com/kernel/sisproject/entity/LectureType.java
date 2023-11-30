package com.kernel.sisproject.entity;

import org.springframework.util.StringUtils;

public enum LectureType {
	SCORE,
	PASS_FAIL;

	public static LectureType getLectureType(String typeName) {
		if (!StringUtils.hasText(typeName)) return null;
		for (LectureType type : LectureType.values()) {
			if (type.name().equalsIgnoreCase(typeName)) {
				return type;
			}
		}
		throw new IllegalArgumentException("맞는 타입 없음 : " + typeName);
	}
}
