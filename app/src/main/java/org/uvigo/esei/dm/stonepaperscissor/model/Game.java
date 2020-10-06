package org.uvigo.esei.dm.stonepaperscissor.model;

public class Game {


    public enum RoundResult{
        WIN,
        DRAW,
        LOOSE;
    }

    private int round;


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public RoundResult evaluateDecision(Player player1, Player player2){
        if((player1.getDecision().equals(Player.Decision.PAPER)
            && player2.getDecision().equals(Player.Decision.STONE))
            ||
                (player1.getDecision().equals(Player.Decision.STONE)
                        && player2.getDecision().equals(Player.Decision.SCISSOR))
            ||
                (player1.getDecision().equals(Player.Decision.SCISSOR)
                        && player2.getDecision().equals(Player.Decision.PAPER))){

            player1.incrementPartialScore();
            this.round = this.round + 1;
            return RoundResult.WIN;
        }else if(!player1.getDecision().equals(player2.getDecision())){
            player2.incrementPartialScore();
            this.round = this.round + 1;
            return RoundResult.LOOSE;
        }
        return RoundResult.DRAW;

    }


}
