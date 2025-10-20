package calculator.core;

import java.util.regex.Pattern;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMITERS = ",|:";      // 기본 구분자(쉼표, 콜론)
    private static final String ESCAPED_NEWLINE = "\\n";         // 리터럴 줄바꿈 표식

    private StringAddCalculator() {
    }

    /**
     * 외부에서 호출하는 메인 API: 문자열을 받아 합계를 계산한다.
     * 요구사항:
     *  - 빈 문자열(null/"")은 0 반환
     *  - 기본 구분자(, :) 또는 커스텀 구분자(//X\n) 기준으로 숫자를 분리
     *  - 숫자가 아닌 값/빈 토큰/음수는 IllegalArgumentException 발생
     */
    public static int add(String input) {
        if (isNullOrEmpty(input)) {
            return 0;
        }

        String[] tokens = split(input);
        int sum = 0;

        for (String token : tokens) {
            String value = token.trim();

            validateNotEmpty(value);
            validateNotNegative(value);
            validateAllDigits(value);

            sum += Integer.parseInt(value);
        }

        return sum;
    }

    /**
     * 입력 문자열을 기본/커스텀 구분자 기준으로 분리한다.
     * 현재 단계: 리터럴 "\\n"만 지원(실제 줄바꿈 문자는 이후 리팩터링에서 추가 예정).
     * 커스텀 구분자가 들어오면 해당 구분자만 사용해 분리한다.
     */
    public static String[] split(String input) {
        if (isNullOrEmpty(input)) {
            return new String[0];
        }

        if (hasCustomHeader(input)) {
            return splitWithCustomDelimiterLiteralNewline(input);
        }

        return input.split(DEFAULT_DELIMITERS);
    }

    // ====== Validation helpers ======

    private static void validateNotEmpty(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
        }
    }

    private static void validateNotNegative(String value) {
        // 음수는 첫 글자가 '-' 인 경우로 판단하여 별도의 메시지로 명확히 처리
        if (!value.isEmpty() && value.charAt(0) == '-') {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + value);
        }
    }

    private static void validateAllDigits(String value) {
        // 숫자만 허용(0 포함). 공백/기타 문자는 모두 실패.
        if (!value.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + value);
        }
    }

    // ====== Parser helpers ======

    private static boolean hasCustomHeader(String input) {
        return input.startsWith("//");
    }

    /**
     * 리터럴 "\\n"을 경계로 커스텀 구분자를 추출하고, 이후 숫자 영역을 해당 구분자와 기본 구분자로 split 한다.
     */
    private static String[] splitWithCustomDelimiterLiteralNewline(String input) {
        int boundaryIndex = input.indexOf(ESCAPED_NEWLINE);
        if (boundaryIndex == -1) {
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. (\\n 누락)");
        }

        String customDelimiter = extractCustomDelimiter(input, boundaryIndex);
        String numbersPart = extractNumbersPartAfterEscapedNewline(input, boundaryIndex);

        // 커스텀 구분자와 기본 구분자를 함께 처리한다.
        // 예: //;\n1;2:3  -> [1, 2, 3]
        String combinedPattern = Pattern.quote(customDelimiter) + "|" + DEFAULT_DELIMITERS;

        return numbersPart.split(combinedPattern);
    }

    private static String extractCustomDelimiter(String input, int endExclusive) {
        String delimiter = input.substring(2, endExclusive); // "//" 이후부터 경계 전까지
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
        }
        if (delimiter.length() != 1) {
            // 미션 요구: 커스텀 구분자는 한 글자
            throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다: " + delimiter);
        }
        return delimiter;
    }

    private static String extractNumbersPartAfterEscapedNewline(String input, int boundaryIndex) {
        // ESCAPED_NEWLINE == "\\n" 이므로 길이는 2
        return input.substring(boundaryIndex + ESCAPED_NEWLINE.length());
    }

    // ====== Common helpers ======

    private static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
