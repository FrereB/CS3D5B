package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DifficultyChooser extends AppCompatActivity {
    private String name;
    private int type;
    private String uri;
    private String picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_chooser);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        name = extras.getString(LoginPage.EXTRA_NAME);
        type = extras.getInt("type");
        if (type == 1){
            picture = extras.getString("picture");
        } else if (type == 2) {
            uri = extras.getString("imageUri");
        }
    }

    //Action to go to the game against computer with level easy

    protected void chooseEasy(View view) {

        Intent intent = new Intent(this, PVC.class);
        Bundle extras = getExtras();
        double errorRate = 0.8;
        intent.putExtra("errorRate",errorRate);
        intent.putExtras(extras);
        startActivity(intent);
    }

    //Action to go to the game against computer with level medium

    protected void chooseMedium(View view) {

        Intent intent = new Intent(this, PVC.class);
        Bundle extras = getExtras();
        double errorRate = 0.6;
        intent.putExtra("errorRate",errorRate);
        startActivity(intent);
    }

    //Action to go to the game against computer with level hard

    protected void chooseHard(View view) {

        Intent intent = new Intent(this, PVC.class);
        Bundle extras = getExtras();
        double errorRate = 0.4;
        intent.putExtra("errorRate",errorRate);
        startActivity(intent);
    }

    //Action to go to the game against computer with level expert

    protected void chooseExpert(View view) {

        Intent intent = new Intent(this, PVC.class);
        Bundle extras = getExtras();
        double errorRate = 0.2;
        intent.putExtra("errorRate",errorRate);
        startActivity(intent);
    }

    protected Bundle getExtras(){
        Bundle extras = new Bundle();
        extras.putString("name",name);
        extras.putInt("type",type);
        if (type == 1){
            extras.putString("picture",picture);
        } else if (type == 2) {
            extras.putString("uri",uri);
        }
        return extras;
    }
}
