package com.qbqb.kyubi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WheelActivity extends AppCompatActivity {

    ImageView wheeling;
    String[] sectors = {"shot di zombie", "mina lo prende in culo", "scopati 2 bambine", "rutta al telefono con tua mamma", "caga in testa a uno sbirro", "bevi come un drago", "3 shot di tequila", "rapisci un nigeriano", "cagati addosso", "vai a farti con la tipa alla tua destra", "2 giri", "mi scopo tua madre"};
    TextView tv;
    Button btnBack;

    //----------------------------------------------------------------------------------------
    //                                      MAIN
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);

        wheeling = findViewById(R.id.wheel);
        tv = findViewById(R.id.txtv);
        Collections.reverse(Arrays.asList(sectors));

        //Button --> "←"
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuModalitaActivity();
            }
        });

    }

    public void spinWheel(View view){
        Random rand = new Random();
        final int degree = rand.nextInt(360);
        RotateAnimation rA = new RotateAnimation(0,degree + 720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rA.setDuration(3000);
        rA.setFillAfter(true);
        rA.setInterpolator(new DecelerateInterpolator());
        rA.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                CalculatePoint(degree);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        wheeling.startAnimation(rA);
    }

    public void CalculatePoint(int degree){
        int initialPoint = 0;
        int endPoint = 30;

        int i = 0;
        String res = null;
        do {
            if(degree >= initialPoint && degree < endPoint)
                res = sectors[i];
            initialPoint += 30;
            endPoint += 30;
            i++;

        } while(res == null);

        tv.setText(res);

    }

    private void openMenuModalitaActivity() {
        Intent intent = new Intent(this, MenuModalitaActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }
}


