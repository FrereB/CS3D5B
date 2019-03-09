package trinity.cs3d5b.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "trinity.cs3d5b.quizz.CATEGORY";
    public static final String EXTRA_NAME = "trinity.cs3d5b.quizz.NAME";
    public static final String EXTRA_PICTURE = "trinity.cs3d5b.quizz.PICTURE";

    //private String categoryString;
    //private String name = "";
    //private String image = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        /*final String categoryMessage = (String) category1.getText();

        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCategory(categoryMessage);
            }
        });
        */
    }

    /*protected void onClickCategory(String catMessage){
        Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
        intent.putExtra(EXTRA_CATEGORY, catMessage);
        startActivity(intent);
    }
    */

    public void categoryClick(View view) {

        String categoryMessage;
        Bundle stats = new Bundle();
        Intent mIntent = new Intent(this, MainActivity.class);
        String name = getIntent().getStringExtra(LoginPage.EXTRA_NAME);
        String image = getIntent().getStringExtra(LoginPage.EXTRA_PICTURE);
        mIntent.putExtra(EXTRA_NAME,name);
        mIntent.putExtra(EXTRA_PICTURE,image);

        categoryMessage = (String) ( (Button) findViewById(view.getId())).getText();
        System.out.print(categoryMessage);
        stats.putString("category",categoryMessage );
        mIntent.putExtras(stats);
        mIntent.putExtra(EXTRA_CATEGORY,categoryMessage);
        setResult(RESULT_OK, mIntent);startActivity(mIntent);
    }
}
