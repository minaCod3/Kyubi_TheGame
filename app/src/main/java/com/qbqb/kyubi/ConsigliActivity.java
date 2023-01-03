package com.qbqb.kyubi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.FileInputStream;
import java.io.InputStream;

public class ConsigliActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------
    //                                    ATTRIBUTI
    //----------------------------------------------------------------------------------------
    Button btnBack;
    TextView txtConsigli;
    String file = "consigli.txt";

    //----------------------------------------------------------------------------------------
    //                                      MAIN
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consigli);

        //Button --> "←"
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        //TextView Generale
        txtConsigli = findViewById(R.id.textView);

        //Lettura da File
        String text = "";
        try{
            InputStream iS = getAssets().open(file);
            int size = iS.available();
            byte[] buffer = new byte[size];
            iS.read(buffer);
            iS.close();
            text = new String(buffer);
            txtConsigli.setText(text);
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