package com.example.sendintentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    private static boolean flag;
    private ImageView imagePng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imagePng = findViewById(R.id.imageChangeView);
        changeImage();

        Button buttonChange = findViewById(R.id.button_change_png);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });
    }

    public void changeImage() {
        if (flag) {
            imagePng.setImageDrawable(getDrawable(R.drawable.outline_check_circle_black_48dp));
            flag = false;
        } else {
            imagePng.setImageDrawable(getDrawable(R.drawable.outline_build_black_48dp));
            flag = true;
        }
    }
}
