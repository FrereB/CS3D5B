package trinity.cs3d5b.quizz;



import android.os.Bundle;
import android.content.*;
import android.widget.*;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.graphics.*;
import android.net.*;
import android.database.Cursor;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.*;
import android.support.v4.content.*;


public class LoginPage extends AppCompatActivity  {

    public static final String EXTRA_NAME = "trinity.cs3d5b.quizz.NAME";
    public static final String EXTRA_PICTURE = "trinity.cs3d5b.quizz.PICTURE";
    private static final int PICK_PICTURE_REQUEST =1; //The request code for avatar picture
    public static final int IMAGE_GALLERY_REQUEST = 12;
    public static final int STORAGE_PERMISSION_CODE = 13;
    private ImageView pictureProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

    }


    protected void goToMainActivity(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = findViewById(R.id.name);

        if (editText.getText().toString().trim().equals("")) {
            editText.setError("Required!");
        }
        else {

            String name = editText.getText().toString();
            intent.putExtra(EXTRA_NAME, name);
            ImageView tvpic = findViewById(R.id.picturechoose);

            if (tvpic.getTag() != null) {
                String picture = tvpic.getTag().toString();
                intent.putExtra(EXTRA_PICTURE, picture);
            }

            startActivity(intent);
        }
    }

    protected void goToLeaderboard(View view) {

        Intent intent = new Intent(this, LeaderBoard.class);
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

                int id = getResources().getIdentifier(picture, "drawable", getPackageName());
                ImageView1.setImageResource(id);
                ImageView1.setTag(picture);
            }
        }






    }



//Action to go to selection of the picture
    protected void goToPicture(View view) {
        Intent intent = new Intent(this,ProfilePicture.class);
        startActivityForResult(intent,PICK_PICTURE_REQUEST);
    }



}



