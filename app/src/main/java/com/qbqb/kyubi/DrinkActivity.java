package com.qbqb.kyubi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.InputStream;

public class DrinkActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------
    //                                    ATTRIBUTI
    //----------------------------------------------------------------------------------------
    Button btnBack;
    TextView txtDrink;
    String file = "drink.txt";

    //----------------------------------------------------------------------------------------
    //                                      MAIN
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Button --> "←"
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        //TextView Generale
        txtDrink = findViewById(R.id.textView);
        txtDrink.setMovementMethod(new ScrollingMovementMethod());
        //Lettura da File
        String text = "";
        try{
            InputStream iS = getAssets().open(file);
            int size = iS.available();
            byte[] buffer = new byte[size];
            iS.read(buffer);
            iS.close();
            text = new String(buffer);
            txtDrink.setText(text);
        }catch(Exception e){ }
    }

    //----------------------------------------------------------------------------------------
    //                                   ALTRE FUNZIONI
    //----------------------------------------------------------------------------------------
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }
}