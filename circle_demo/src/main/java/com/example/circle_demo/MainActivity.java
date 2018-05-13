package com.example.circle_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.circle_demo.diyview.MyView;
import com.example.circle_demo.diyview.On_Off_View;

public class MainActivity extends AppCompatActivity {

    private On_Off_View viewf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LinearLayout linearLayout = findViewById(R.id.linear);
//        MyView myView = new MyView(this);
//        linearLayout.addView(myView);

        viewf = findViewById(R.id.on_off_view);

        viewf.setonselectlistener(new On_Off_View.selectlistener() {
            @Override
            public void isClickOpen(boolean isopens) {
                Toast.makeText(MainActivity.this, ""+isopens, Toast.LENGTH_SHORT).show();
                
            }
        });

    }
}
