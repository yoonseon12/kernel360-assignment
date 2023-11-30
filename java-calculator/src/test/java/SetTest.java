import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SetTest {
	private Set<Integer> numbers;

	@BeforeEach
	void setUp() {
		numbers = new HashSet<>();
		numbers.add(1);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
	}

	@Test
	@DisplayName("Set의 size() 메소드를 활용해 Set의 크기를 확인한다.")
	void testSet1() {
		Set<Integer> input = numbers;
		int expected = 3;

		assertThat(input.size()).isEqualTo(expected);
	}

	@Test
	@DisplayName("Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지 확인한다. :: 중복코드 발생")
	void testSet2_1() {
		Set<Integer> input = numbers;
		int expected = 3;

		assertThat(numbers.contains(1)).isTrue();
		assertThat(numbers.contains(2)).isTrue();
		assertThat(numbers.contains(3)).isTrue();
	}

	@ParameterizedTest
	@DisplayName("Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지 확인한다. :: 중복코드 해결")
	@ValueSource(ints = {1, 2, 3})
	void testSet2_2(int number) {
		assertThat(numbers.contains(number)).isTrue();
	}

	@ParameterizedTest
	@DisplayName("Set의 contains() 메소드를 활용해 값이 존재하면 true, 존재하지 않으면 false를 반환한다.")
	@CsvSource(value = {"1^true", "2^true", "3^true", "4^false", "5^false"}, delimiter = '^')
	void testSet3(int number, boolean status) {
		assertThat(numbers.contains(number)).isEqualTo(status);
	}
}
