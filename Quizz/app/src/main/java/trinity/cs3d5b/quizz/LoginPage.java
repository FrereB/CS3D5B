package trinity.cs3d5b.quizz;



import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.provider.MediaStore;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;



public class LoginPage extends AppCompatActivity  {

    public static final String EXTRA_NAME = "trinity.cs3d5b.quizz.NAME";
    public static final String EXTRA_PICTURE = "trinity.cs3d5b.quizz.PICTURE";
    public static String picture;
    public static Uri uriSelectedImage;

    public static final int PICTURE = 1;//The request code for profil picture;

    //public static final String EXTRA_CATEGORY = "trinity.cs3d5b.quizz.CATEGORY";
    private final int PICK_PICTURE_REQUEST = 1; //The request code
    private final int CATEGORY_REQUEST = 3;   //Other request code

    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        category = "";
    }


    //Go to Main Activity
    protected void goToMainActivity(final View view) {


        Intent intent = new Intent(this, CategoryActivity.class);
        EditText editText = findViewById(R.id.name);
        //We give the informaion to the main activity
        Intent intent = new Intent(this, MainActivity.class);
        EditText userName = findViewById(R.id.name);


      //Verification if the user has a username or not => Mandatory
        if (userName.getText().toString().trim().equals("")) {
            userName.setError("Required!");
        }


//Verification if the user has a profile picture or not => Mandatory
        else if(uriSelectedImage==null && picture==null){
            //If not we display an Alert box to force him to put choose a picture
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginPage.this);
            builder.setCancelable(true);
            builder.setTitle("You need to choose a profil picture");

            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //After the close of the dialog box we open the page to choose the profil picture
                    goToPicture(view);

                }
            });

            builder.show();



        }

        else {
//If every information are complet, we can go to the quizz part
            String name = userName.getText().toString();
            intent.putExtra(EXTRA_NAME, name);
            Bundle stats = new Bundle();
            if(picture!=null) {

                stats.putString("picture", picture);
                stats.putInt("type", 1);
                intent.putExtras(stats);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
            else if(uriSelectedImage!=null){
                stats.putInt("type", 2);
                intent.putExtras(stats);
                intent.putExtra("imageUri",uriSelectedImage);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
            if (tvpic.getTag() != null) {
                String picture = tvpic.getTag().toString();
                intent.putExtra(EXTRA_PICTURE, picture);
            }

            goToCategory();
            //intent.putExtra(EXTRA_CATEGORY, category);

            startActivity(intent);
        }
    }

    //Action to go to the LeaderBoard

    protected void goToLeaderboard(View view) {

        Intent intent = new Intent(this, LeaderBoard.class);
        startActivity(intent);
    }

// Receive result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);
        if (intent != null) {
            Bundle extras = intent.getExtras();
            ImageView profilePicture = (ImageView) findViewById(R.id.picturechoose);

            if (requestCode == PICTURE) {

                if (resultCode == RESULT_OK) {

                    //We get back the picture chosen

                    picture = extras.getString("picture");
                    int type = extras.getInt("type");


                    if (type == 1) { // Photo from the gallery of the user
                        //We get the id and we display the picture
                        int id = getResources().getIdentifier(picture, "drawable", getPackageName());
                        profilePicture.setImageResource(id);
                        profilePicture.setTag(picture);
                    } else if (type == 2) { // Avatar already available
                        //We get the uri and we display the picture
                        uriSelectedImage = intent.getParcelableExtra("imageUri");

                        //All the path of the picture from the user phone
                        String[] filePathCol = {MediaStore.Images.Media.DATA};

                        //Cursor to access to the path of the picture
                        Cursor cursor = this.getContentResolver().query(uriSelectedImage, filePathCol, null, null, null);
                        cursor.moveToFirst();

                        //We recover the path of the picture

                        int columIndex = cursor.getColumnIndex(filePathCol[0]);
                        String imgPath = cursor.getString(columIndex);
                        cursor.close();
                        //get the Image
                        Bitmap image = BitmapFactory.decodeFile(imgPath);
                        //Display the picture

                        profilePicture.setImageBitmap(image);
                    }
                }


            }
        }
                if(picture.equals("avatar6")) {
                    ImageView1.setImageDrawable(getDrawable(R.drawable.avatar6));
                    ImageView1.setTag("avatar6");
                }
            }
        }

        else if (requestCode == CATEGORY_REQUEST){
            if(resultCode == RESULT_OK){
                category = extras.getString("category");
            }
        }
    }



//Action to go to selection of the picture
    protected void goToPicture(View view) {
        Intent intent = new Intent(this,ProfilePicture.class);
        startActivityForResult(intent,PICTURE);
    }

    protected void goToCategory(){

        Intent intent = new Intent(this, CategoryActivity.class);
        startActivityForResult(intent,CATEGORY_REQUEST);

        return;
    }
}




