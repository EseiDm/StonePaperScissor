package org.uvigo.esei.dm.stonepaperscissor.model;


import java.util.Random;

public class Player {

    public enum Decision{
        STONE(0, "STONE"),
        PAPER(1, "PAPER"),
        SCISSOR(2, "SCISSOR");
        int value;
        String label;
        Decision(int value, String label){
            this.value = value;
            this.label = label;
        }
        public int getValue() {
            return value;
        }
        public String getLabel() {
            return label;
        }
    }

    private Decision decision;

    private int score = 0;
    private int partialScore = 0;

    public int getScore() {
        return score;
    }

    public int getPartialScore() {
        return partialScore;
    }

    public void setPartialScore(int partialScore) {
        this.partialScore = partialScore;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public void calculateAutomaticDecision(){
        Random random = new Random();
        int decisionValue = random.nextInt(3);
        if (decisionValue == 0)
            this.decision = Decision.STONE;
        else if (decisionValue == 1)
            this.decision = Decision.PAPER;
        else
            this.decision = Decision.SCISSOR;
    }

    public void incrementScore(){
        this.score = this.score  + 1;
    }

    public void incrementPartialScore(){
        this.partialScore = this.partialScore  + 1;
    }
}
