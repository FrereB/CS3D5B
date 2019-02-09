package trinity.cs3d5b.quizz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.util.*;
import android.content.*;
import android.widget.*;



public class LoginPage extends AppCompatActivity  {

    private final int PICK_PICTURE_REQUEST =1; //The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

    }


    protected void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

/// receive result/////////////////////////////////////////////////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);
        Bundle extras = intent.getExtras();

        if (requestCode == PICK_PICTURE_REQUEST) {

            if (resultCode == RESULT_OK) {
                String picture = extras.getString("picture");


               ImageView ImageView1 = (ImageView) findViewById(R.id.picturechoose);
               if(picture.equals("men")) {
                   ImageView1.setImageDrawable(getDrawable(R.drawable.picturemen));
               }

                if(picture.equals("women")) {
                    ImageView1.setImageDrawable(getDrawable(R.drawable.picturewomen));
                }

            }
        }
    }




    protected void goToPicture(View view) {
        Intent intent = new Intent(this,ProfilePicture.class);
        startActivityForResult(intent,PICK_PICTURE_REQUEST);
    }
}



