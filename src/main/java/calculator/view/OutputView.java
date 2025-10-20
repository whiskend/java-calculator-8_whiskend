package calculator.view;

import java.util.Arrays;

public class OutputView {
    private OutputView() {
        // 객체 생성 방지
    }

    public static void printPrompt(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public static void printInput(String input){
        System.out.println("입력값 확인: " + input);
    }

    // split 결과 테스트
    public static void printSplitResult(String[] numbers){
        System.out.println("분리 결과: " + Arrays.toString(numbers));
    }
}
