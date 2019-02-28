package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import trinity.cs3d5b.quizz.authentication.AuthCache;


public class EndScreenActivity extends AppCompatActivity {

    TextView score;
    public static final String EXTRA_NAME = "trinity.cs3d5b.quizz.NAME";
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
//
        resetButton = findViewById(R.id.reset_button);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        final String scoreMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        score = findViewById(R.id.textView2);
        score.setText(scoreMessage);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EndScreenActivity.this, "New Game", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EndScreenActivity.this, LoginPage.class);

                startActivity(intent);
            }
        });


        Button bShare = (Button) findViewById(R.id.share_button);


        //Share score on button click
        bShare.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //get Score of the user
                //Sharing intent
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Here is my Score on Quizz: " + scoreMessage + " Try do better! ");
                startActivity(Intent.createChooser(shareIntent, "Share your Score via"));


            }

        });

        AuthCache.Companion.logout();
    }


    //Action to go to the leaderboard
    protected void goToLeaderboard(View view) {

        Intent intent = new Intent(this, LeaderBoard.class);
        startActivity(intent);
    }


}
