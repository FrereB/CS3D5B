package trinity.cs3d5b.quizz;

import android.support.v7.app.AppCompatActivity;
import android.widget.*;
import android.util.*;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import trinity.cs3d5b.quizz.utilities.JsonParser;
import trinity.cs3d5b.quizz.utilities.LeaderBoardAdapter;

public class LeaderBoard extends AppCompatActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText login;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);


        JsonParser jsonParser = new JsonParser(this.getApplicationContext());
        List<JSONObject> leaderBoard = new ArrayList<>();
        try{
            JSONArray array = jsonParser.getLeaderBoard();
            for(int x=0;x<array.length();x++){
                leaderBoard.add(array.getJSONObject(x));
            }
        } catch (Exception e){}

        //List<JSONObject> sortedLeaderBoard = insertionSort(leaderBoard);

        LeaderBoardAdapter leaderBoardAdapter = new LeaderBoardAdapter(getApplicationContext(),leaderBoard);

        ((GridView) findViewById(R.id.grid)).setAdapter(leaderBoardAdapter);

    }

    private List<JSONObject> insertionSort(List<JSONObject> input){
        try {
            JSONObject temp;
            JSONObject temp2;
            for (int i = 1; i < input.size(); i++) {
                for(int j = i ; j > 0 ; j--){
                    if(input.get(j).getInt("score") < input.get(j-1).getInt("score")){
                        temp = input.get(j);
                        temp2 = input.get(j-1);
                        input.add(j,temp2);
                        input.add(j-1,temp);
                    }
                }
            }
        } catch (Exception e){}
        return input;
    }
}
