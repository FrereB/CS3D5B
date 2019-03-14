package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import java.util.concurrent.TimeUnit;

import trinity.cs3d5b.quizz.authentication.AuthCache;
import trinity.cs3d5b.quizz.database.PictureEncoder;
import trinity.cs3d5b.quizz.database.UserDatabase;
import trinity.cs3d5b.quizz.database.UserModel;

import static trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.PICTURE_TYPE_AVATAR;
import static trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.PICTURE_TYPE_UPLOAD;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "trinity.cs3d5b.quizz.MESSAGE";

    private String Qlib = "";

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary(this);

    private static String picture;
    private UserModel userModel;

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private Button mQuitButton;
    private Question currentQuestion;

    private String mAnswer;
    private String name;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private boolean gameOver = false;
    //timer code
    private TextView timerTextView;
    private CounterClass timer;
    long remainMilli = 0;
    boolean isRunning = false;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.clock);
        mediaPlayer.start();

        // Get the Intent that started this activity and extract the strings
        Intent intent = getIntent();
        name = intent.getStringExtra(CategoryActivity.EXTRA_NAME);
        Qlib = intent.getStringExtra(CategoryActivity.EXTRA_CATEGORY);


        mQuestionLibrary.setQuestionLibrary(Qlib);

        if (!AuthCache.Companion.isLoggedIn()) {
            throw new IllegalArgumentException("Not logged in");
        }
        userModel = AuthCache.Companion.getUserModel();

        ImageView profilePicture = (ImageView) findViewById(R.id.pictureprofile);
        switch (userModel.getProfilePictureType()) {
            case PICTURE_TYPE_AVATAR:
                //We get the id and we display the picture
                int id = getResources().getIdentifier(userModel.getProfilePictureData(),
                        "drawable", getPackageName());
                profilePicture.setImageResource(id);
                break;

            case PICTURE_TYPE_UPLOAD:
                String base64 = userModel.getProfilePictureData();
                Bitmap image = new PictureEncoder().decodeBase64ToBitmap(base64);
                profilePicture.setImageBitmap(image);
                break;

            default:
                throw new IllegalArgumentException("Unknown profile picture type = "
                        + userModel.getProfilePictureType());
        }

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.pseudo);
        textView.setText(userModel.getName());

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

                if (mButtonChoice1.getText().equals(mAnswer)) {
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

                if (mButtonChoice2.getText().equals(mAnswer)) {
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

                if (mButtonChoice3.getText().equals(mAnswer)) {
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

                if (mButtonChoice4.getText().equals(mAnswer)) {
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
                mediaPlayer.release();
                Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EndScreenActivity.class);
                String message = Integer.toString(mScore);

                // Submit current score to leaderboard
                UserModel currentUser = AuthCache.Companion.getUserModel();
                if (currentUser != null) {
                    currentUser.setHighScore(mScore);

                    UserDatabase userDatabase = new UserDatabase();
                    userDatabase.update(currentUser.getId(), currentUser, null);
                }

                //String numOfQsMessage = Integer.toString(mQuestionLibrary.getNumberOfQuestions());
                String numOfQsMessage = Integer.toString(4);

                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }

    private void updateQuestion() {
        mediaPlayer.release();

        if (!gameOver) {
            mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.clock);
            mediaPlayer.start();

            currentQuestion = mQuestionLibrary.getQuestion(mQuestionNumber);

            mQuestionView.setText(currentQuestion.getQuestion());
            mButtonChoice1.setText(currentQuestion.getAnswers().get(0));
            mButtonChoice2.setText(currentQuestion.getAnswers().get(1));
            mButtonChoice3.setText(currentQuestion.getAnswers().get(2));
            mButtonChoice4.setText(currentQuestion.getAnswers().get(3));

            mAnswer = currentQuestion.getCorrectAnswer();

            mQuestionNumber++;

            if (mQuestionNumber >= mQuestionLibrary.getNumberOfQuestions()) {

                gameOver = true;
            }
        } else {
            Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, EndScreenActivity.class);
            String scoreMessage = Integer.toString(mScore);

            // Submit score to leaderboard
            UserModel currentUser = AuthCache.Companion.getUserModel();
            if (currentUser != null) {
                currentUser.setHighScore(mScore);

                UserDatabase userDatabase = new UserDatabase();
                userDatabase.update(currentUser.getId(), currentUser, null);
            }

            //String numOfQsMessage = Integer.toString(mQuestionLibrary.getNumberOfQuestions());

            intent.putExtra(EXTRA_MESSAGE, scoreMessage);
            startActivity(intent);
        }
        timer.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        timer.cancel();
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
            long seconds = TimeUnit.MILLISECONDS.toSeconds(remainMilli)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainMilli));
            long millis = TimeUnit.MILLISECONDS.toMillis(remainMilli) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(remainMilli));
            long maxSeconds = 15;

            //Format to display the timer
            String hms = String.format("%02d . %03d", seconds, millis);

            timerTextView.setText(hms);

            float log1=(float)(Math.log(maxSeconds-seconds)/Math.log(maxSeconds));
            mediaPlayer.setVolume(log1,log1);

        }

        //When time is finished, what should happen: will go in this method
        @Override
        public void onFinish() {
            // reset all variables
            isRunning=false;
            remainMilli=0;
            Toast.makeText(MainActivity.this, "Out of Time!", Toast.LENGTH_SHORT).show();
            updateQuestion();
        }
    }
}
