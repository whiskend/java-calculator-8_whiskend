package calculator;

import calculator.view.InputView;
import calculator.view.OutputView;
import calculator.core.StringAddCalculator;

public class Application {
    public static void main(String[] args) {
        OutputView.printPrompt(); // "덧셈할 문자열을 입력해주세요."
        String input = InputView.readInput(); // 사용자 입력 받기

        //기본 구분자 + 커스텀 구분자 분리 테스트
        String[] numbers = StringAddCalculator.split(input);
        OutputView.printSplitResult(numbers);

        int result = StringAddCalculator.add(input);
        OutputView.printResult(result);
    }
}
