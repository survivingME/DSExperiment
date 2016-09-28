/**
 * Created by Tremble on 2016/9/19.
 */
public class calculator {
    private String formula;
    private char[] formulaArray;
    double number = 0;
    double result = 0;
    double integerNumber = 0;
    double decimalNumber = 0;
    myStack<Character> optr = new myStack<Character>();
    myStack<Double> opnd = new myStack<Double>();
    private char[] operator = {'+', '-', '*', '/', '(', ')', '#'};
    private int[][] priority = {{0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 0},
            {1, 1, 1 ,1, 1, -1, -2},
            {0, 0, 0, 0, -2, 0, 0},
            {1, 1, 1, 1, 1, -2, -3}};
    public calculator(String formula) {
        this.formula = formula;
    }

    public String calculate() {
        formulaArray = formula.toCharArray();
        optr.push('#');
        label1:
        for(int i = 0;i < formulaArray.length;i++) {
            if(formulaArray[i] >= 48 && formulaArray[i] <= 57) {
                while(formulaArray[i] >= 48 && formulaArray[i] <= 57) {
                    if((formulaArray[i+1] < 48 || formulaArray[i+1] > 57) && formulaArray[i+1] != '.') {
                        integerNumber =integerNumber * 10 + Double.parseDouble(String.valueOf(formulaArray[i]));
                        number = integerNumber + decimalNumber;
                        opnd.push(number);
                        number = integerNumber = decimalNumber = 0;
                    } else {
                        integerNumber = integerNumber * 10 + Double.parseDouble(String.valueOf(formulaArray[i]));
                        if(formulaArray[i+1] == '.') {
                            i += 2;
                            while(formulaArray[i] >= 48 && formulaArray[i] <= 57) {
                                int count  = 1;
                                decimalNumber += Double.parseDouble(String.valueOf(formulaArray[i])) * Math.pow(10, -count);
                                i++;
                            }
                            number = integerNumber + decimalNumber;
                            opnd.push(number);
                            number = integerNumber = decimalNumber = 0;
                            i--;
                            continue label1;
                        }
                    }
                    i++;
                }
                i--;
            } else if(formulaArray[i] == '+' || formulaArray[i] == '-' || formulaArray[i] == '*'
                    || formulaArray[i] == '/' || formulaArray[i] == '(' || formulaArray[i] == ')' || formulaArray[i] == '#') {
                if(operatorOperation(formulaArray[i])=="E") {
                    return "Error";
                }
            } else if(formulaArray[i] == 'E') {
                return "Error";
            }
        }
        return String.valueOf(opnd.top());
    }

    public String operatorOperation(char noperator) {
        int col = 0;
        int row = 0;
        for(int i = 0;i < operator.length;i++) {
            if (operator[i] == noperator) {
                col = i;
                break;
            }
        }
        for(int i = 0;i < operator.length;i++) {
            if (operator[i] == optr.top()) {
                row = i;
                break;
            }
        }
        if(priority[row][col] == 1) {
            optr.push(noperator);
        } else if(priority[row][col] == 0) {
            if(compute(opnd.pop(), opnd.pop(), optr.pop())=="Error") {
                return "Error";
            }
            operatorOperation(noperator);
        } else if(priority[row][col] == -1) {
            optr.pop();
        } else if(priority[row][col] == -2) {
            return "Error";
        } else if(priority[row][col] == -3) {

        }
        return "";
    }

    public String compute(double nNumber, double pNumber, char operator) {
        switch(operator) {
            case '+':
                result = pNumber+nNumber;
                opnd.push(result);
                break;
            case '-':
                result = pNumber-nNumber;
                opnd.push(result);
                break;
            case '*':
                result = pNumber*nNumber;
                opnd.push(result);
                break;
            case '/':
                if(nNumber == 0) {
                    return "Error";
                }
                result = pNumber / nNumber;
                opnd.push(result);
                break;
        }
        return "";
    }
}
