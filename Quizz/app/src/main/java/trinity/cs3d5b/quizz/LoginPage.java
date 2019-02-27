package trinity.cs3d5b.quizz;



import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.content.*;
import android.provider.MediaStore;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;



public class LoginPage extends AppCompatActivity  {

    public static final String EXTRA_NAME = "trinity.cs3d5b.quizz.NAME";
    public static final String EXTRA_PICTURE = "trinity.cs3d5b.quizz.PICTURE";
    public static final int IMAGE_GALLERY_REQUEST = 2;
    public static final int AVATAR_REQUEST = 1;//The request code for avatar picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


    }


    protected void goToMainActivity(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        EditText userName = findViewById(R.id.name);

        if (userName.getText().toString().trim().equals("")) {
            userName.setError("Required!");
        }
        else {

            String name = userName.getText().toString();
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
        ImageView ImageView1 = (ImageView) findViewById(R.id.picturechoose);

        if (requestCode == AVATAR_REQUEST) {

           if (resultCode == RESULT_OK) {

               String picture = extras.getString("picture");
               int type = extras.getInt("type");


                    if(type == 1){

                   if (picture.equals("avatar1")) {
                       ImageView1.setImageDrawable(getDrawable(R.drawable.avatar1));


                   }

                   if (picture.equals("avatar2")) {
                       ImageView1.setImageDrawable(getDrawable(R.drawable.avatar2));

                   }

                   if (picture.equals("avatar3")) {
                       ImageView1.setImageDrawable(getDrawable(R.drawable.avatar3));

                   }

                   if (picture.equals("avatar4")) {
                       ImageView1.setImageDrawable(getDrawable(R.drawable.avatar4));

                   }

                   if (picture.equals("avatar5")) {
                       ImageView1.setImageDrawable(getDrawable(R.drawable.avatar5));


                   }

                   if (picture.equals("avatar6")) {
                       ImageView1.setImageDrawable(getDrawable(R.drawable.avatar6));

                   }


               } else if (type==2) {


                        byte[] byteArray = intent.getByteArrayExtra("image");
                        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                   ImageView1.setImageBitmap(bmp);
               }
           }




        }






    }



//Action to go to selection of the picture
    protected void goToPicture(View view) {
        Intent intent = new Intent(this,ProfilePicture.class);
        startActivityForResult(intent,AVATAR_REQUEST);
    }



}



