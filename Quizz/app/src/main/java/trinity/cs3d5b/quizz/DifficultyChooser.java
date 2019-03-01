package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DifficultyChooser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_chooser);
    }

    //Action to go to the game against computer with level easy
    protected void chooseEasy(View view) {
        Intent intent = new Intent(this, PVC.class);
        double errorRate = 0.8;
        intent.putExtra("errorRate",errorRate);
        startActivity(intent);
    }

    //Action to go to the game against computer with level medium
    protected void chooseMedium(View view) {
        Intent intent = new Intent(this, PVC.class);
        double errorRate = 0.6;
        intent.putExtra("errorRate",errorRate);
        startActivity(intent);
    }

    //Action to go to the game against computer with level hard
    protected void chooseHard(View view) {
        Intent intent = new Intent(this, PVC.class);
        double errorRate = 0.4;
        intent.putExtra("errorRate",errorRate);
        startActivity(intent);
    }

    //Action to go to the game against computer with level expert
    protected void chooseExpert(View view) {
        Intent intent = new Intent(this, PVC.class);
        double errorRate = 0.2;
        intent.putExtra("errorRate",errorRate);
        startActivity(intent);
    }
}
