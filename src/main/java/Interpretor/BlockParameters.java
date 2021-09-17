package Interpretor;

public class BlockParameters {
    private final String leftSide;
    private final String rightSide;
    private final String operator;
    
    public BlockParameters(String leftSide, String rightSide, String operator) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    public String getOperator() {
        return operator;
    }
}
