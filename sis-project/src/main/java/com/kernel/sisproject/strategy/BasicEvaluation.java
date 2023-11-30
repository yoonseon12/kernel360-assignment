package com.kernel.sisproject.strategy;

public class BasicEvaluation implements GradeEvaluation {
	@Override
	public String getGrade(int point) {
		if(point >= 90 && point <= 100) return "A";
		if(point >= 80 && point <= 89) return "B";
		if(point >= 70 && point <= 79) return "C";
		if(point >= 55 && point <= 69) return "D";
		return "F";
	}
}
