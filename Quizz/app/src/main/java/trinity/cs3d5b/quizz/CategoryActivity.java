package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.net.*;

public class CategoryActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "trinity.cs3d5b.quizz.CATEGORY";
    public static final String EXTRA_NAME = "trinity.cs3d5b.quizz.NAME";

    //private String categoryString;
    //private String name = "";
    //private String image = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    public void categoryClick(View view) {

        String categoryMessage;
        Bundle stats = new Bundle();
        Intent mIntent = new Intent(this, MainActivity.class);
        String name = getIntent().getStringExtra(LoginPage.EXTRA_NAME);

        Bundle extras = getIntent().getExtras();
        String picture = extras.getString("picture");
        int type = extras.getInt("type");
        if(picture != null) {
            stats.putString("picture", picture);
        }
         Uri uriSelectedImage = mIntent.getParcelableExtra("imageUri");
        if(uriSelectedImage!=null) {
            mIntent.putExtra("imageUri", uriSelectedImage);
        }
        stats.putInt("type", type);
        mIntent.putExtras(stats);

        mIntent.putExtra(EXTRA_NAME,name);

        categoryMessage = (String) ( (Button) findViewById(view.getId())).getText();

        stats.putString("category",categoryMessage );

        mIntent.putExtra(EXTRA_CATEGORY,categoryMessage);
       setResult(RESULT_OK, mIntent);
        startActivity(mIntent);
    }
}
