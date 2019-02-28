package trinity.cs3d5b.quizz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.widget.*;



public class LoginPage extends AppCompatActivity  {

    public static final String EXTRA_NAME = "trinity.cs3d5b.quizz.NAME";
    public static final String EXTRA_PICTURE = "trinity.cs3d5b.quizz.PICTURE";
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


    protected void goToMainActivity(View view) {


        Intent intent = new Intent(this, CategoryActivity.class);
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

            goToCategory();
            //intent.putExtra(EXTRA_CATEGORY, category);

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


              if(picture.equals("avatar1")) {
                   ImageView1.setImageDrawable(getDrawable(R.drawable.avatar1));
                   ImageView1.setTag("avatar1");

               }

                if(picture.equals("avatar2")) {
                    ImageView1.setImageDrawable(getDrawable(R.drawable.avatar2));
                    ImageView1.setTag("avatar2");
                }

                if(picture.equals("avatar3")) {
                    ImageView1.setImageDrawable(getDrawable(R.drawable.avatar3));
                    ImageView1.setTag("avatar3");
                }

                if(picture.equals("avatar4")) {
                    ImageView1.setImageDrawable(getDrawable(R.drawable.avatar4));
                    ImageView1.setTag("avatar4");
                }

                if(picture.equals("avatar5")) {
                    ImageView1.setImageDrawable(getDrawable(R.drawable.avatar5));
                    ImageView1.setTag("avatar5");

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
        startActivityForResult(intent,PICK_PICTURE_REQUEST);
    }

    protected void goToCategory(){

        Intent intent = new Intent(this, CategoryActivity.class);
        startActivityForResult(intent,CATEGORY_REQUEST);

        return;
    }
}




