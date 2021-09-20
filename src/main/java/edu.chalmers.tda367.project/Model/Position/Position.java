package main.java.edu.chalmers.tda367.project.Model.Position;

public class Position implements IPosition{

    private final PositionRuleType positionRuleType;

    protected Position( PositionRuleType positionRuleType ){
        if (positionRuleType != PositionRuleType.ORIGIN_POSITION && positionRuleType != PositionRuleType.STANDARD_POSITION) {
            throw new IllegalArgumentException("The positionRuleType " + positionRuleType.toString() + "is not allowed for ordinary positions.");
        }
        this.positionRuleType = positionRuleType;
    }
}
