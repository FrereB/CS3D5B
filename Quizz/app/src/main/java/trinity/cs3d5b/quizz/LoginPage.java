package trinity.cs3d5b.quizz;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import java.io.*;
import android.net.*;
import android.widget.*;
import android.graphics.*;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    private  static ImageView imgPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        ImageView optionman = (ImageView) findViewById(R.id.PictureMan);
        optionman.setOnClickListener(this);
        ImageView optionwoman = (ImageView) findViewById(R.id.PictureWoman);
        optionwoman.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.PictureMan :
                Toast.makeText(this,"Clicked optionman",Toast.LENGTH_SHORT).show();
                break;

            case R.id.PictureWoman :
                Toast.makeText(this,"Clicked optionwoman",Toast.LENGTH_SHORT).show();
                break;
        }

    }


    protected void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }



}



