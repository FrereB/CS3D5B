package trinity.cs3d5b.quizz;

import android.support.v7.app.AppCompatActivity;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import trinity.cs3d5b.quizz.utilities.JsonParser;

public class MainActivity extends AppCompatActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText login;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonParser jsonParser = new JsonParser(this.getApplicationContext());
        String leaderBoard = "";
        try{
            leaderBoard = jsonParser.getLeaderBoard().getString(0);
        } catch (Exception e){}
        TextView text = findViewById(R.id.test);
        text.setText(leaderBoard);

    }
}
