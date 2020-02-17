package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //deklarasi variabel
    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switch1;
    SeekBar seekBar;
    boolean fanStatus;
    final int SPEED[] = {0, 6000, 5000, 2000, 800};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById((R.id.toggleButton));
        imageView = (ImageView) findViewById(R.id.imageView);
        switch1 = (Switch) findViewById(R.id.switch1);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean on = ((ToggleButton) v).isChecked();
                if (on){
                    rotateAnimator.setDuration(SPEED[seekBar.getProgress()]);
                    rotateAnimator.start();
                }
                else{
                    rotateAnimator.end();
                }
            }
        });

        switch1.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v){
                boolean on = ((Switch) v).isChecked();
                if(on){
                    gd.setColors(new int[]{Color.YELLOW, Color.TRANSPARENT});
                    imageView.setBackground(gd);
                }
                else {
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            }

        });

        seekBar.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                    rotateAnimator.setDuration(SPEED[progress]);
                    rotateAnimator.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){

            }
        });
    }

}
