package trinity.cs3d5b.quizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import trinity.cs3d5b.quizz.database.UserDatabase;
import trinity.cs3d5b.quizz.database.UserModel;
import trinity.cs3d5b.quizz.utilities.LeaderBoardAdapter;

public class LeaderBoard extends AppCompatActivity {

    private ArrayList<UserModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        UserDatabase userDatabase = new UserDatabase();
        userDatabase.retrieveAll(new UserDatabase.RetrieveCallback() {
            @Override
            public void onSuccess(@NotNull ArrayList<UserModel> userList) {
                items = userList;

                LeaderBoardAdapter leaderBoardAdapter
                        = new LeaderBoardAdapter(getApplicationContext(),items);

                ((GridView) findViewById(R.id.grid)).setAdapter(leaderBoardAdapter);
            }

            @Override
            public void onFailure() {
                Toast.makeText(LeaderBoard.this, "Failed to load leaderboard",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
