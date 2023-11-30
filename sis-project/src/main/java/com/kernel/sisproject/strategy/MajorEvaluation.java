package com.kernel.sisproject.strategy;

public class MajorEvaluation implements GradeEvaluation {
	@Override
	public String getGrade(int point) {
		if(point >= 95 && point <= 100) return "S";
		if(point >= 90 && point <= 94) return "A";
		if(point >= 80 && point <= 89) return "B";
		if(point >= 70 && point <= 79) return "C";
		if(point >= 60 && point <= 69) return "D";
		return "F";
	}
}
