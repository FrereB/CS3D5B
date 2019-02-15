package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EndScreenActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "trinity.cs3d5b.quizz.NAME";
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        resetButton = findViewById(R.id.reset_button);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String scoreMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        final TextView textView = findViewById(R.id.textView2);
        textView.setText(scoreMessage);

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(EndScreenActivity.this, "New Game", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EndScreenActivity.this,LoginPage.class);

                startActivity(intent);
            }
        });
    }

    protected void goToLeaderboard(View view) {

        Intent intent = new Intent(this, LeaderBoard.class);
        startActivity(intent);
    }


}
