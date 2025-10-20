package calculator.view;

public class OutputView {
    private OutputView() {
        // 객체 생성 방지
    }

    /** 사용자에게 입력 프롬프트를 출력한다. */
    public static void printPrompt() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    /** 계산 결과를 출력한다. */
    public static void printResult(int value) {
        System.out.println("결과 : " + value);
    }
}
