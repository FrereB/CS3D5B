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

    //public static final String EXTRA_CATEGORY = "trinity.cs3d5b.quizz.CATEGORY";

    private Button category1;
    private Button category2;
    private Button category3;
    private Button category4;
    private Button category5;
    private Button category6;
    private Button category7;
    private Button category8;
    private Button category9;
    private Button category10;

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


        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);
        category3 = findViewById(R.id.category3);
        category4 = findViewById(R.id.category4);
        category5 = findViewById(R.id.category5);
        category6 = findViewById(R.id.category6);
        category7 = findViewById(R.id.category7);
        category8 = findViewById(R.id.category8);
        category9 = findViewById(R.id.category9);
        category10 = findViewById(R.id.category10);

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

        switch(view.getId()){
            case R.id.category1 :
                categoryMessage = (String) category1.getText();
                System.out.print(categoryMessage);
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();
                //startActivity(mIntent);

            case R.id.category2 :
                categoryMessage = (String) category2.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();

            case R.id.category3 :
                categoryMessage = (String) category3.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();

            case R.id.category4 :
                categoryMessage = (String) category4.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();

            case R.id.category5 :
                categoryMessage = (String) category5.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();

            case R.id.category6 :
                categoryMessage = (String) category6.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();


            case R.id.category7 :
                categoryMessage = (String) category7.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();

            case R.id.category8 :
                categoryMessage = (String) category8.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();

            case R.id.category9 :
                categoryMessage = (String) category9.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();

            case R.id.category10 :
                categoryMessage = (String) category10.getText();
                stats.putString("category",categoryMessage );
                mIntent.putExtras(stats);
                setResult(RESULT_OK, mIntent);
                finish();
        }

    }
}
