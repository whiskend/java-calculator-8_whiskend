package calculator.core;

import java.util.regex.Pattern;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMITERS = ",|:";

    private StringAddCalculator(){}

    public static String[] split(String input){
        // 빈 문자열은 0으로 반환
        if (input == null || input.isEmpty()){
            return new String[0];
        }

        // 커스텀 구분자 처리
        if (input.startsWith("//")) {
            // 우선 리터럴 문자열("\\n")인 경우 지원
            int delimiterEndIndex = input.indexOf("\\n"); // 두 글자 사용: \ + n
            if (delimiterEndIndex == -1) { //에외 처리
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. (\\ㅜ 누락)");
            }

            // "//"와 "\n" 사이 문자 => 커스텀 구분자
            String customDelimiter = input.substring(2, delimiterEndIndex);

            if(customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
            }

            // 나머지 부분은 숫자 문자열
            String numbers = input.substring(delimiterEndIndex + 2);

            return numbers.split(customDelimiter);
        }

        // 기본 구분자(DEFAULT_DELIMITERS)를 기준으로 분리
        return input.split(DEFAULT_DELIMITERS);
    }
}
