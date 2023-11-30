import java.util.Arrays;

public class Calculator {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution("//!\n1!2"));
		System.out.println(s.solution("1:2:3"));
	}

	static class Solution {
		public int solution(String str) {
			if ("".equals(str)) return 0;
			String delimiter = str.contains("//") ? str.substring(2,3) : ",";

			String[] split = str.chars()
				.filter(c -> c != delimiter.charAt(0) && c !=',' && c !=':' && c != '/' && c != '\n')
				.filter(c -> {
					if (!Character.isDigit(c) || c < 0) {
						throw new RuntimeException("숫자 이외의 값 또는 음수");
					}
					return true;
				})
				.mapToObj(c -> String.valueOf((char)c))
				.toArray(String[]::new);

			return Arrays.stream(split)
				.mapToInt(Integer::valueOf)
				.sum();
		}
	}
}
