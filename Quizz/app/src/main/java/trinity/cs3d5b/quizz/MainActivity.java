package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import trinity.cs3d5b.quizz.utilities.JsonParser;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "trinity.cs3d5b.quizz.MESSAGE";

    //private String Qlib = "General Knowledge";
    private String Qlib = "";

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private Button mQuitButton;

    private String mAnswer;
    private String name = "";
    private String image = "";
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private boolean gameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the Intent that started this activity and extract the strings
        Intent intent = getIntent();
        name = intent.getStringExtra(CategoryActivity.EXTRA_NAME);
        image = intent.getStringExtra(CategoryActivity.EXTRA_PICTURE);
        Qlib = intent.getStringExtra(CategoryActivity.EXTRA_CATEGORY);

        //mQuestionLibrary.setQuestionLibrary("General Knowledge");
        mQuestionLibrary.setQuestionLibrary(Qlib);

        ImageView ImageView1 = (ImageView) findViewById(R.id.pictureprofile);
        if(image!=null) {
            if (image.equals("avatar1")) {
                ImageView1.setImageDrawable(getDrawable(R.drawable.avatar1));

            }

            if (image.equals("avatar2")) {
                ImageView1.setImageDrawable(getDrawable(R.drawable.avatar1));
            }

            if (image.equals("avatar3")) {
                ImageView1.setImageDrawable(getDrawable(R.drawable.avatar3));
            }

            if (image.equals("avatar4")) {
                ImageView1.setImageDrawable(getDrawable(R.drawable.avatar4));
            }

            if (image.equals("avatar5")) {
                ImageView1.setImageDrawable(getDrawable(R.drawable.avatar5));
            }

            if (image.equals("avatar6")) {
                ImageView1.setImageDrawable(getDrawable(R.drawable.avatar6));
            }




        }

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.pseudo);
        textView.setText(name);

        mScoreView = findViewById(R.id.score);
        mQuestionView = findViewById(R.id.question);
        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = findViewById(R.id.choice2);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4);
        mQuitButton = findViewById(R.id.quit);
        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mButtonChoice1.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore();
                    updateQuestion();
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mButtonChoice2.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore();
                    updateQuestion();
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mButtonChoice3.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore();
                    updateQuestion();
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mButtonChoice4.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore();
                    updateQuestion();
                    //This line of code is optional
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EndScreenActivity.class);
                String message = Integer.toString(mScore);
                (new JsonParser(getApplicationContext())).addToLeaderBoard(name,message,image);
                //String numOfQsMessage = Integer.toString(mQuestionLibrary.getNumberOfQuestions());
                String numOfQsMessage = Integer.toString(4);

                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }

    private void updateQuestion() {

        if (!gameOver) {
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
            mButtonChoice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);

            mQuestionNumber++;

            if (mQuestionNumber >= mQuestionLibrary.getNumberOfQuestions()) {

                gameOver = true;
            }
        } else {
            Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, EndScreenActivity.class);
            String scoreMessage = Integer.toString(mScore);
            (new JsonParser(getApplicationContext())).addToLeaderBoard(name,scoreMessage,image);
            //String numOfQsMessage = Integer.toString(mQuestionLibrary.getNumberOfQuestions());

            intent.putExtra(EXTRA_MESSAGE, scoreMessage);
            startActivity(intent);
        }
    }

    private void updateScore() {
        mScoreView.setText("" + mScore);
    }

}
