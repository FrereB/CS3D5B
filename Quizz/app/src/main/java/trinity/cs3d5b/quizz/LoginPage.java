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
    public static String picture;
    public static Uri uri;
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

        else if(uri==null && picture==null){
            Toast.makeText(this, "You need to choose a profile picture", Toast.LENGTH_LONG).show();
        }

        else {

            String name = userName.getText().toString();
            intent.putExtra(EXTRA_NAME, name);
            ImageView tvpic = findViewById(R.id.picturechoose);

            if (tvpic.getTag() != null) {
                String picturepi = tvpic.getTag().toString();
                intent.putExtra(EXTRA_PICTURE, picturepi);
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

                picture = extras.getString("picture");
               int type = extras.getInt("type");


                    if(type == 1){
                        int id = getResources().getIdentifier(picture, "drawable", getPackageName());
                        ImageView1.setImageResource(id);
                        ImageView1.setTag(picture);
                    } else if (type==2) {

                         uri = intent.getParcelableExtra("imageUri");

                        //All the path of the picture from the user phone
                        String[] filePathCol = {MediaStore.Images.Media.DATA};

                        //Cursor to access to the path of the picture
                        Cursor cursor = this.getContentResolver().query(uri,filePathCol,null, null,null);
                        cursor.moveToFirst();

                        //We recover the path of the picture

                        int columIndex = cursor.getColumnIndex(filePathCol[0]);
                        String imgPath = cursor.getString(columIndex);
                        cursor.close();
                        //Récupération Image
                        Bitmap image = BitmapFactory.decodeFile(imgPath);


                        //byte[] byteArray = intent.getByteArrayExtra("image");
                        //Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                   ImageView1.setImageBitmap(image);
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



