package calculator.core;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMITERS = ",|:";

    private StringAddCalculator(){
    }

    public static String[] splitByDefaultDelimiter(String input){
        // 빈 문자열은 0으로 반환
        if (input == null || input.isEmpty()){
            return new String[0];
        }
        // 기본 구분자(DEFAULT_DELIMITERS)를 기준으로 분리
        return input.split(DEFAULT_DELIMITERS);
    }
}
