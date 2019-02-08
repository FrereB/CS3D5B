package trinity.cs3d5b.quizz;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import java.io.*;
import android.net.*;
import android.widget.*;
import android.graphics.*;

public class LoginPage extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    private ImageView imgPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //get a reference to the image view that the user will see
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
    }


    protected void goToAnActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This method will be invoked when the user clicks to choose his picture
     * @param view
     */

    protected void onImageGalleryClicked(View view) {
        Intent intentPhoto = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);//localisation of the data
        String path = pictureDirectory.getPath();
        //Get a URI representation
        Uri data = Uri.parse(path);

        //set the data and type(all image types)

        intentPhoto.setDataAndType(data,"image/*");

        //We will invoke this activity

        startActivityForResult(intentPhoto,IMAGE_GALLERY_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode ==RESULT_OK) {// everything processed successfully
            if (requestCode == IMAGE_GALLERY_REQUEST) { //

                Uri imageUri = data.getData();//The address of the image on the SD card

                //Declare a stream to read the image data from the SD card
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);

                    //Show the image to the user
                    imgPicture.setImageBitmap(image);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Unable to open image", (Toast.LENGTH_LONG));
                }

            }
        }
    }
}
