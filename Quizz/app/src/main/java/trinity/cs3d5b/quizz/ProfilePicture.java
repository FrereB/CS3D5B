package trinity.cs3d5b.quizz;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilePicture extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 12;
    public static final int STORAGE_PERMISSION_CODE = 13;
    public static final int AVATAR_REQUEST = 14;
    private ImageView pictureProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);
        Button bChoose = (Button) findViewById(R.id.PictureGallery);

        bChoose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(ProfilePicture.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    imageGalleryAccess(v);

                } else {
                    requestStoragePermission();
                }
            }


        });


        pictureProfile = (ImageView) findViewById(R.id.galleryPic);
    }

    // Permission necessary for the API above 23
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to access to the gallery")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ProfilePicture.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }




    //This will be invoked when the user want to choose an image from his gallery

    public void imageGalleryAccess(View v) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //We define that we want to pick something

        //We define where to find the data
        String picturePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();


        //We get an URI
        Uri dataPicture = Uri.parse(picturePath);

        //Set the data and define the type we want : all picture type

        photoPicker.setDataAndType(dataPicture, "image/*");
        startActivityForResult(photoPicker, IMAGE_GALLERY_REQUEST); ///We share the information

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) { // Check if the user chose OK

            if (requestCode == IMAGE_GALLERY_REQUEST ) { // Display the picture from the gallery of the user that he chose

                //Acces to the image from data
                Uri selectedImage = data.getData();

                //All the path of the picture from the user phone
                String[] filePathCol = {MediaStore.Images.Media.DATA};

                //Cursor to access to the path of the picture
                Cursor cursor = this.getContentResolver().query(selectedImage,filePathCol,null, null,null);
                cursor.moveToFirst();

                //We recover the path of the picture

                int columIndex = cursor.getColumnIndex(filePathCol[0]);
                String imgPath = cursor.getString(columIndex);
                cursor.close();
                //Récupération Image
                Bitmap image = BitmapFactory.decodeFile(imgPath);

                //Display of the picture
                pictureProfile.setImageBitmap(image);




            }

            else if (requestCode == AVATAR_REQUEST) {
                String picture = data.getExtras().getString("picture");
                ImageView ImageView1 = (ImageView) findViewById(R.id.galleryPic);


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



        }







    protected void goToAvatar(View view) {

        Intent intent = new Intent(this, AvatarChoice.class);
        startActivityForResult(intent, AVATAR_REQUEST);
    }


    protected void goToLoginPage(View view) {

        Intent intent = new Intent(this,LoginPage.class);
        startActivity(intent);
    }
}
