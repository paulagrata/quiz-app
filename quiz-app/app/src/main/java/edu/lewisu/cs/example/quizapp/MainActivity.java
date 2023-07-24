package edu.lewisu.cs.example.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int lifetimeScore = 25;
    private TextView mainScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainScoreTextView = findViewById(R.id.mainScoreTextView);
        String scoreString  = getResources().getString(R.string.lifetime_score_string, lifetimeScore);
        mainScoreTextView.setText(scoreString);
        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();

                            int gameScore = 0;
                            if (intent != null) {
                                gameScore = intent.getIntExtra("score", 0);
                            }

                            //update lifetime score and related TextView
                            lifetimeScore += gameScore;
                            String scoreString = getResources().getString(R.string.lifetime_score_string, lifetimeScore);
                            mainScoreTextView.setText(scoreString);
                        }
                    }
                });


        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(view -> {
            Intent quizIntent = new Intent(getApplicationContext(), QuizActivity.class);
            quizIntent.putExtra("lifetime", lifetimeScore);
            startForResult.launch(quizIntent);
        });

    }
}