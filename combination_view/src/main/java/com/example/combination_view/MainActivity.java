package com.example.combination_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Add_Sub_View addSubView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSubView = findViewById(R.id.add_sub);
        addSubView.setMaxValue(20);


        addSubView.setOnButtonClickListenter(new Add_Sub_View.OnButtonClickListenter() {
            @Override
            public void onButtonAddOrSubClick(int value) {

                    Toast.makeText(MainActivity.this, ""+value, Toast.LENGTH_SHORT).show();

            }
        });



    }


}
