package com.kernel.sisproject.strategy;

public class PassFailEvaluation implements GradeEvaluation {
	@Override
	public String getGrade(int point) {
		if(point >= 70 && point <= 100) return "P";
		return "F";
	}
}
