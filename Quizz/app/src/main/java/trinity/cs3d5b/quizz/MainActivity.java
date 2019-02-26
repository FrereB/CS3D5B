package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import trinity.cs3d5b.quizz.utilities.JsonParser;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "trinity.cs3d5b.quizz.MESSAGE";

    private String Qlib = "General Knowledge";

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary(Qlib);

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

    //timer code
    private TextView timerTextView;
    private CounterClass timer;
    long remainMilli = 0;
    boolean isRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        name = intent.getStringExtra(LoginPage.EXTRA_NAME);
        image = intent.getStringExtra(LoginPage.EXTRA_PICTURE);

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

        timerTextView = findViewById(R.id.timerTextView);
        timer = new CounterClass(15000,1);
        timer.start();
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

    public class CounterClass extends CountDownTimer {
        //All three methods (constructor) need to be overridden to use this class

        //Default Constructor
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        //When timer is ticking, what should happen at that duration; will go in this method
        @Override
        public void onTick(long millisUntilFinished) {
            remainMilli = millisUntilFinished;

            //Format to display the timer
            String hms = String.format("%02d . %03d",
                    TimeUnit.MILLISECONDS.toSeconds(remainMilli)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainMilli)),
                    TimeUnit.MILLISECONDS.toMillis(remainMilli) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(remainMilli)));

            timerTextView.setText(hms);

        }

        //When time is finished, what should happen: will go in this method
        @Override
        public void onFinish() {
            // reset all variables
            isRunning=false;
            remainMilli=0;
            Toast.makeText(MainActivity.this, "Out of Time!", Toast.LENGTH_SHORT).show();
            updateQuestion();
            timer.start();
        }
    }
}
