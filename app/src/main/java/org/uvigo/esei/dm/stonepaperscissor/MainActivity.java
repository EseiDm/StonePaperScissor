package org.uvigo.esei.dm.stonepaperscissor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.uvigo.esei.dm.stonepaperscissor.model.Game;
import org.uvigo.esei.dm.stonepaperscissor.model.Player;

public class MainActivity extends AppCompatActivity {

    private Game game = new Game();
    private Player player1 = new Player();
    private Player player2 = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.buttonStone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDecision(Player.Decision.STONE);
            }
        });

        button = findViewById(R.id.buttonPaper);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDecision(Player.Decision.PAPER);
            }
        });


        button = findViewById(R.id.buttonScissor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDecision(Player.Decision.SCISSOR);
            }
        });

        initPlayerScores();

    }

    private void processDecision(Player.Decision decision) {

        player1.setDecision(decision);
        player2.calculateAutomaticDecision();
        Game.RoundResult result = game.evaluateDecision(player1, player2);

        //decision IA
        TextView textView = findViewById(R.id.textViewDecisionPlayer2);
        textView.setText(player2.getDecision().getLabel());



        if (!result.equals(Game.RoundResult.DRAW)){
            if (game.getRound()==1){
                textView = findViewById(R.id.textViewResultDecision1);
                textView.setText(result.name());
                textView = findViewById(R.id.textViewResultDecision2);
                textView.setText("");
                textView = findViewById(R.id.textViewResultDecision3);
                textView.setText("");
            }else if (game.getRound()==2){
                textView = findViewById(R.id.textViewResultDecision2);
                textView.setText(result.name());
                textView = findViewById(R.id.textViewResultDecision3);
                textView.setText("");
            }else if (game.getRound()==3){
                textView = findViewById(R.id.textViewResultDecision3);
                textView.setText(result.name());
            }
        }else{
            Toast.makeText(MainActivity.this, result.name(), Toast.LENGTH_SHORT).show();
            if (game.getRound()==0){
                textView = findViewById(R.id.textViewResultDecision1);
                textView.setText("");
                textView = findViewById(R.id.textViewResultDecision2);
                textView.setText("");
                textView = findViewById(R.id.textViewResultDecision3);
                textView.setText("");
            }
        }

        textView = findViewById(R.id.textViewGameInfo);

        if (player1.getPartialScore()>=2){
            textView.setText("YOU WIN!");
            player1.incrementScore();
            completeRound();
        }else if (player2.getPartialScore()>=2){
            textView.setText("YOU LOOSE!");
            player2.incrementScore();
            completeRound();
        }else{
            textView.setText("");
        }
        initPlayerScores();
    }

    private void completeRound() {
        player1.setPartialScore(0);
        player2.setPartialScore(0);
        game.setRound(0);

    }

    private void initPlayerScores() {
        TextView textView;//scores
        textView = findViewById(R.id.textViewScorePlayer1);
        textView.setText(player1.getScore()+"");
        textView = findViewById(R.id.textViewScorePlayer2);
        textView.setText(player2.getScore()+"");
    }
}