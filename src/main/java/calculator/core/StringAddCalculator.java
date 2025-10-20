package calculator.core;

import java.util.regex.Pattern;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMITERS = ",|:";

    private StringAddCalculator(){}

    /**
     * 외부에서 호출하는 메인 API: 문자열을 받아 합계를 계산 */
    public static int add(String input) {
        // 빈 문자열은 합계 0
        if (input == null || input.isEmpty()){
            return 0;
        }

        String[] tokens = split(input);
        int sum = 0;
        for (String token : tokens) {
            String trimmed = token.trim();
            // 빈 토큰인지 검증
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
            }
            // 숫자인지 검증
            if (!trimmed.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함: " + trimmed);
            }
            //양수인지 검증
            int value = Integer.parseInt(trimmed);
            if (value < 0) {
                throw new IllegalArgumentException("음수는 허용 안 됨: " + value);
            }
            sum += value;
        }
        return sum;
    }

    /**
     * 입력 문자열을 기본/커스텀 구분자 기준으로 분리
     * 커스텀 구분자가 들어온 경우 기존 구분자는 제외하고 커스텀 구분자로만 처리
     * 현재 단계: 리터럴 \n만 지원 (실제 줄바꿈은 추후 리팩토링에서 추가)
     */
    public static String[] split(String input){
        // 빈 문자열은 0으로 반환
        if (input == null || input.isEmpty()){
            return new String[0];
        }

        // 커스텀 구분자 처리
        if (input.startsWith("//")) {
            // 우선 리터럴 문자열("\\n")인 경우만 지원
            int delimiterEndIndex = input.indexOf("\\n"); // 두 글자 사용: \ + n
            if (delimiterEndIndex == -1) { //에외 처리
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. (\\n 누락)");
            }

            // "//"와 "\n" 사이 문자 => 커스텀 구분자
            String customDelimiter = input.substring(2, delimiterEndIndex);

            if(customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
            }

            // 나머지 부분은 숫자 문자열
            String numbers = input.substring(delimiterEndIndex + 2);

            // 메타 문자 안전 처리
            return numbers.split(Pattern.quote(customDelimiter));
        }

        // 기본 구분자(DEFAULT_DELIMITERS)를 기준으로 분리
        return input.split(DEFAULT_DELIMITERS);
    }
}
