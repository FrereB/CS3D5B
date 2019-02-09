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
                String num = "men";
                Toast.makeText(this,"Clicked picture 1",Toast.LENGTH_SHORT).show();
                Bundle stats = new Bundle();
                stats.putString("picture",num );
                Intent mIntent = new Intent();
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();


            case R.id.Picture2 :
                String num2 = "women";
                Toast.makeText(this,"Clicked picture 2",Toast.LENGTH_SHORT).show();
                Bundle stats2 = new Bundle();
                stats2.putString("picture",num2 );
                Intent mIntent2 = new Intent();
                mIntent2.putExtras(stats2);
                setResult(RESULT_OK, mIntent2);
                finish();


        }

    }
}
