package calculator;

import calculator.view.InputView;
import calculator.view.OutputView;
import calculator.core.StringAddCalculator;

public class Application {
    public static void main(String[] args) {
        OutputView.printPrompt();
        String input = InputView.readInput();

        int result = StringAddCalculator.add(input);
        OutputView.printResult(result);
    }
}
