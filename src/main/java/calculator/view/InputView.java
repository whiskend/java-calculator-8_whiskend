package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private InputView() {
        // 객체 생성 방지
    }

    public static String readInput(){
        return Console.readLine();
    }
}
