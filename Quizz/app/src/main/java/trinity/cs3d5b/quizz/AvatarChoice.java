package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AvatarChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_choice);
    }


    public void imageClick(View view) {

        String temp =getResources().getResourceEntryName(view.getId());
        String num = "avatar"+temp.charAt(temp.length()-1);
        System.out.println(num);
        Bundle stats = new Bundle();
        stats.putString("picture",num );
        Intent mIntent = new Intent();
        mIntent.putExtras(stats);
        setResult(RESULT_OK, mIntent);
        finish();

    }
}
