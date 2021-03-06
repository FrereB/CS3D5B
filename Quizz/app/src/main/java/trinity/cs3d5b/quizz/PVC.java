package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import trinity.cs3d5b.quizz.authentication.AuthCache;
import trinity.cs3d5b.quizz.database.PictureEncoder;
import trinity.cs3d5b.quizz.database.UserDatabase;
import trinity.cs3d5b.quizz.database.UserModel;

import static trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.PICTURE_TYPE_AVATAR;
import static trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.PICTURE_TYPE_UPLOAD;
public class PVC extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "trinity.cs3d5b.quizz.MESSAGE";
    private QuestionLibrary mQuestionLibrary;


    private TextView mScoreView;


    private TextView cScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private Button mQuitButton;
    private Question currentQuestion;

    private String mAnswer;
    private UserModel userModel;
    private double errorRate;
    private int mScore = 0;
    private int cScore = 0;
    private int mQuestionNumber = 0;
    private boolean gameOver = false;

    //timer code
    private TextView timerTextView;
    private PVC.CounterClass timer;
    long remainMilli = 0;
    boolean isRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

      mQuestionLibrary = new QuestionLibrary(this);
        mQuestionLibrary.setQuestionLibrary("General Knowledge");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvc);

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

        Intent intent = getIntent();
        errorRate = intent.getDoubleExtra("errorRate", 0);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.pseudo);
        textView.setText(userModel.getName());

        timerTextView = findViewById(R.id.timerTextView);
        timer = new PVC.CounterClass(15000, 1);
        timer.start();
        cScoreView = findViewById(R.id.computerScore);
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
                updateComputerScore();
                if (mButtonChoice1.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore();
                    updateQuestion();
                    Toast.makeText(PVC.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PVC.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateComputerScore();
                if (mButtonChoice2.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore();
                    updateQuestion();
                    Toast.makeText(PVC.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PVC.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateComputerScore();
                if (mButtonChoice3.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore();
                    updateQuestion();
                    Toast.makeText(PVC.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PVC.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateComputerScore();
                if (mButtonChoice4.getText() == mAnswer) {
                    mScore = mScore + 1;
                    updateScore();
                    updateQuestion();
                    //This line of code is optional
                    Toast.makeText(PVC.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PVC.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PVC.this, "Game Over", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PVC.this, EndScreenActivity.class);
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

    private void updateComputerScore(){
        double random = Math.random();
        if (random > errorRate){
            cScore += 1;
            cScoreView.setText("" + cScore);
        }
    }

    private void updateQuestion() {

        if (!gameOver) {
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
            Toast.makeText(PVC.this, "Game Over", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PVC.this, PVCEndScreen.class);
            String playerScore = Integer.toString(mScore);
            String computerScore = Integer.toString(cScore);
            String message = "";

            if ( mScore > cScore){
                message = "Congratulations! you won against the computer.";
            } else if (mScore == cScore){
                message = "The results are in! It's a tie! Better luck next time.";
            } else {
                message = "Too bad! You lost against the computer! better luck next time.";
            }

            // Submit score to leaderboard
            UserModel currentUser = AuthCache.Companion.getUserModel();
            if (currentUser != null) {
                currentUser.setHighScore(mScore);

                UserDatabase userDatabase = new UserDatabase();
                userDatabase.update(currentUser.getId(), currentUser, null);
            }

            //String numOfQsMessage = Integer.toString(mQuestionLibrary.getNumberOfQuestions());
            intent.putExtra("endMessage",message);
            intent.putExtra("cScore",computerScore);
            intent.putExtra("pScore", playerScore);
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

            //Format to display the timer
            String hms = String.format("%02d . %03d",
                    TimeUnit.MILLISECONDS.toSeconds(remainMilli) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainMilli)),
                    TimeUnit.MILLISECONDS.toMillis(remainMilli) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(remainMilli)));

            timerTextView.setText(hms);

        }

        //When time is finished, what should happen: will go in this method
        @Override
        public void onFinish() {
            // reset all variables
            updateComputerScore();
            isRunning = false;
            remainMilli = 0;
            Toast.makeText(PVC.this, "Out of Time!", Toast.LENGTH_SHORT).show();
            updateQuestion();
        }
    }
}
