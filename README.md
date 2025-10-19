# java-calculator-precourse 기능 목록
## 1. 입력
- camp.nextstep.edu.missionutils.Console의 readLine()을 활용하여 사용자의 입력을 받는다.
- 입력된 문자열이 null이거나 빈 문자열이면 0을 반환한다.
## 2. 구분자 파싱
- 커스텀 구분자를 처리한다.
- 기본 구분자와 커스텀 구분자를 기준으로 문자열을 분리한다.
- 커스텀 구분자가 올바르지 않은 형식이면 IllegalArgumentException(예외)를 발생시킨다.

## 3. 문자열 검증 및 변환
- 구분자를 기준으로 분리된 각 문자열을 정수로 변환한다.
- 숫자가 아닌 값이나 음수가 포함된 경우 IllegalArgumentException(예외)를 발생시킨다.
## 4. 계산
- 모든 숫자의 합을 계산한다.
- 계산 결과를 반환한다.
## 5. 출력
- 결과 : {합계}
