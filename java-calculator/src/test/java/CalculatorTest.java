import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {
	@ParameterizedTest
	@DisplayName("쉼표(,) 또는 콜론(:)을 구분자로 가지는 1부터 5까지 구성된 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합인 15를 반환한다.")
	@ValueSource(strings = {"1:2,3:4,5", "1,2,3,4,5", "1:2:3:4:5"})
	void test1(String input) {
		int expected = 15;

		Calculator.Solution solution = new Calculator.Solution();
		int output = solution.solution(input);

		Assertions.assertThat(expected).isEqualTo(output);
	}

	@ParameterizedTest
	@DisplayName("쉼표(,) 또는 콜론(:)외에 문자열 앞부분의 “//”와 “\\n” 사이에 위치하는 문자를 커스텀 구분자를 기준으로 분리한 각 숫자의 합인 10를 반환한다.")
	@ValueSource(strings = {"//!\n1!2!3!4", "//@\n1@2@3,4", "//^\n1^2^3:4"})
	void test2(String input) {
		int expected = 10;

		Calculator.Solution solution = new Calculator.Solution();
		int output = solution.solution(input);

		Assertions.assertThat(expected).isEqualTo(output);
	}

	@Test
	@DisplayName("숫자 이외의 값을 전달하는 경우 RuntimeException 예외가 발생한다.")
	void test3() {
		String input = "//!\na!b";
		Calculator.Solution solution = new Calculator.Solution();

		assertThrows(RuntimeException.class, () -> solution.solution(input));
	}

	@Test
	@DisplayName("음수를 전달하는 경우 RuntimeException 예외가 발생한다.")
	void test4() {
		String input = "1:-3,2";
		Calculator.Solution solution = new Calculator.Solution();

		assertThrows(RuntimeException.class, () -> solution.solution(input));
	}
}