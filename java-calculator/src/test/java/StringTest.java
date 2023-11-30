import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringTest {
	@Test
	@DisplayName("\"1,2\" 를 \",\" 로 split 했을 때 1과 2로 잘 분리되는지 확인한다.")
	void testString1_1() {
		String input = "1,2";

		String[] split = input.split(",");

		// 예상되는 컬렉션의 모든 요소가 실제 컬렉션에 포함되어 있는지 확인하며 순서와 요소 모두 정확하게 일치해야한다.
		assertThat(split).contains("1", "2");
	}

	@Test
	@DisplayName("\"1\"을 \",\"로 split 했을 때 1만을 포함하는 배열이 반환되는지 확인한다.")
	void testString1_2() {
		String input = "1";

		String[] split = input.split(",");

		// 예상되는 컬렉션의 모든 요소가 실제 컬렉션에 포함되어 있는지 확인하며 순서 요소는 고려하지 않는다.
		assertThat(split).containsExactly("1");
	}

	@Test
	@DisplayName("\"(1,2)\"를 substring()을 활용해 ()을 제거하고 \"1,2\"를 반환한다.")
	void testString2() {
		String input = "(1,2)";
		String expected = "1,2";

		String output = input.substring(0, input.length()-1).substring(1);

		assertThat(output).isEqualTo(expected);
	}

	@Test
	@DisplayName("\"abc\"에서 String의 charAt()을 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생한다.")
	void testString3() {
		String input = "abc";

		assertThrows(
			StringIndexOutOfBoundsException.class, () -> input.charAt(100));
	}
}
