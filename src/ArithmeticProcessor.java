
class ArithmeticProcessor {

    String processCalculation(String operation, int first, int second) throws ArithmeticException {
        int result = 0;
        switch(operation) {
            case "ADD": result = first + second;
                break;
            case "SUBTRACT": result = first - second;
                break;
            case "MULTIPLY": result = first*second;
                break;
            case "DIVIDE": {
                if (second != 0) {
                    result = first / second;
                } else {
                    throw new ArithmeticException("Division by zero!");
                }
            }
            break;
        }
        return result + "";
    }

}
