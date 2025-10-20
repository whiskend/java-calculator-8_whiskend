package calculator;

import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printPrompt(); // "덧셈할 문자열을 입력해주세요."
        String input = InputView.readInput(); // 사용자 입력 받기
        OutputView.printInput(input); // 입력 잘 들어갔는지 테스트용
    }
}
