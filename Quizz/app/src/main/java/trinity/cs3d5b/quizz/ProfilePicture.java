package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilePicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);
    }



    public void imageClick(View view) {

        switch(view.getId()){
            case R.id.Picture1 :
                String num = "avatar1";
                Toast.makeText(this,"Clicked picture 1",Toast.LENGTH_SHORT).show();
                Bundle stats = new Bundle();
                stats.putString("picture",num );
                Intent mIntent = new Intent();
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();


            case R.id.Picture2 :
                String num2 = "avatar2";
                Toast.makeText(this,"Clicked picture 2",Toast.LENGTH_SHORT).show();
                Bundle stats2 = new Bundle();
                stats2.putString("picture",num2 );
                Intent mIntent2 = new Intent();
                mIntent2.putExtras(stats2);
                setResult(RESULT_OK, mIntent2);
                finish();

            case R.id.Picture3 :
                String num3 = "avatar3";
                Toast.makeText(this,"Clicked picture 3",Toast.LENGTH_SHORT).show();
                Bundle stats3 = new Bundle();
                stats3.putString("picture",num3 );
                Intent mIntent3 = new Intent();
                mIntent3.putExtras(stats3);
                setResult(RESULT_OK, mIntent3);
                finish();

            case R.id.Picture4 :
                String num4 = "avatar4";
                Toast.makeText(this,"Clicked picture 4",Toast.LENGTH_SHORT).show();
                Bundle stats4 = new Bundle();
                stats4.putString("picture",num4 );
                Intent mIntent4 = new Intent();
                mIntent4.putExtras(stats4);
                setResult(RESULT_OK, mIntent4);
                finish();

            case R.id.Picture5 :
                String num5 = "avatar5";
                Toast.makeText(this,"Clicked picture 5",Toast.LENGTH_SHORT).show();
                Bundle stats5 = new Bundle();
                stats5.putString("picture",num5 );
                Intent mIntent5 = new Intent();
                mIntent5.putExtras(stats5);
                setResult(RESULT_OK, mIntent5);
                finish();

            case R.id.Picture6 :
                String num6 = "avatar6";
                Toast.makeText(this,"Clicked picture 6",Toast.LENGTH_SHORT).show();
                Bundle stats6 = new Bundle();
                stats6.putString("picture",num6 );
                Intent mIntent6 = new Intent();
                mIntent6.putExtras(stats6);
                setResult(RESULT_OK, mIntent6);
                finish();


        }

    }
}
