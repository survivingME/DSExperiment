/**
 * Created by Tremble on 2016/9/18.
 */
public class filter {
    private String formula;
    private String nFormula = "";
    private char[] formulaArray;
    private int count;

    public filter(String formula) {
        this.formula = formula;
    }

    public String filterFormula() {
        formulaArray = formula.toCharArray();
        //filter chars
        for(int i = 0;i < formulaArray.length;i++) {
            if(formulaArray[i] >= 48 && formulaArray[i] <= 57) {

            } else if(formulaArray[i] == '+' || formulaArray[i] == '-' || formulaArray[i] == '*'
                    || formulaArray[i] == '/' || formulaArray[i] == '(' || formulaArray[i] == ')' || formulaArray[i] == '#' || formulaArray[i] == '.') {}
            else {formulaArray[i] = ':';
            }
        }
        //bracket adjustment
        int bracketCount = 0;
        for(int i = 0;i < formulaArray.length-1;i++) {
            if(formulaArray[i] == '(' && formulaArray[i+1] != ')') {
                bracketCount += 1;
            } else if (formulaArray[i] == ')' && formulaArray[i+1] != '(') {
                bracketCount -= 1;
            }
            if(bracketCount < 0 || (formulaArray[i] == '(' && formulaArray[i+1] == ')') || (formulaArray[i] == ')' && formulaArray[i+1] == '(')) {
                return "Error";
            }
        }
        if(bracketCount != 0) {
            return "Error";
        }
        //filter operator
        for(int i = 1;i < formulaArray.length-1;i++) {
            if(formulaArray[i] == '+' || formulaArray[i] == '-' || formulaArray[i] == '*' || formulaArray[i] == '/') {
                if((formulaArray[i-1] >= 48 && formulaArray[i-1] <= 58) || formulaArray[i-1] == ')') {
                    if((formulaArray[i+1] >= 48 && formulaArray[i+1] <= 58) || formulaArray[i+1] == '(') {}
                    else {return "Error";}
                } else {return "Error";}
            }
        }
        //char[] to String
        for(int i = 0;i < formulaArray.length;i++) {
            if(formulaArray[i] != ':') {
                nFormula += formulaArray[i];
            }
        }
        return nFormula;
    }
}
