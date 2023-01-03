package com.qbqb.kyubi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------
    //                                    ATTRIBUTI
    //----------------------------------------------------------------------------------------
    TextView linkMina;
    TextView linkDevid;
    Button btnGioca;
    Button btnDrink;
    Button btnConsigli;

    //----------------------------------------------------------------------------------------
    //                                      MAIN
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link a Instagram Davide
        linkMina = (TextView)findViewById(R.id.txtInstaMina);
        linkMina.setMovementMethod(LinkMovementMethod.getInstance());
        //Link a Instagram Devid
        linkDevid = (TextView)findViewById(R.id.txtInstaRosa);
        linkDevid.setMovementMethod(LinkMovementMethod.getInstance());

        //Button --> "Gioca"
        btnGioca = (Button) findViewById(R.id.btnGioco);
        btnGioca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreGiocoActivity();

            }
        });

        //Button --> "Drink"
        btnDrink = (Button) findViewById(R.id.btnDrink);
        btnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrinkActivity();
            }
        });

        //Button --> "Consigli"
        btnConsigli = (Button) findViewById(R.id.btnConsigli);
        btnConsigli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConsigliActivity();
            }
        });
    }

    //----------------------------------------------------------------------------------------
    //                                 ALTRE FUNZIONI
    //----------------------------------------------------------------------------------------
    //APERTURA PREGIOCO ACTIVITY
    private void openPreGiocoActivity() {
        Intent intent = new Intent(this, PreGiocoActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    //APERTURA DRINK ACTIVITY
    private void openDrinkActivity() {
        Intent intent = new Intent(this, DrinkActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    //APERTURA CONSIGLI ACTIVITY
    private void openConsigliActivity() {
        Intent intent = new Intent(this, ConsigliActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }
}