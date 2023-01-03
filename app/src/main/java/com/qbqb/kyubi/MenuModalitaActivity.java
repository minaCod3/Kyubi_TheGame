package com.qbqb.kyubi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuModalitaActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------
    //                                    ATTRIBUTI
    //----------------------------------------------------------------------------------------
    Button btnLastManStanding;
    Button btnRedHotChiliPeppers;
    Button btnCivilWar;
    Button btnDurataPre;
    Button btnDurataPost;
    Button btnBack;

    TextView txtDurata;

    public static int modalita;
    //----------------------------------------------------------------------------------------
    //                                          MAIN
    //----------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_modalita);

        //TextView -> Testo Durata
        txtDurata = (TextView)findViewById(R.id.txtDurataGioco);

        //Button --> "Durata Pre"
        btnDurataPre = (Button) findViewById(R.id.btnDurataPre);
        btnDurataPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String aux = txtDurata.getText().toString();
               if(aux.equals("normal"))
                   txtDurata.setText("easy");
               else if(aux.equals("hard"))
                   txtDurata.setText("normal");
               else if(aux.equals("easy"))
                   txtDurata.setText("hard");
            }
        });

        //Button --> "Durata Post"
        btnDurataPost = (Button) findViewById(R.id.btnDurataPost);
        btnDurataPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux = txtDurata.getText().toString();
                if(aux.equals("normal"))
                    txtDurata.setText("hard");
                else if(aux.equals("easy"))
                    txtDurata.setText("normal");
                else if(aux.equals("hard"))
                    txtDurata.setText("easy");
            }
        });

        //Button --> "←"
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreGiocoActivity();
            }
        });

        //Button --> "The Last Man Standing
        btnLastManStanding = (Button) findViewById(R.id.btnLastMan);
        btnLastManStanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScriviModalita("1");
                modalita = 1;
                OpenGiocoActivity();
            }
        });

        //Button --> "Red Hot Chili Peppers"
        btnRedHotChiliPeppers = (Button) findViewById(R.id.btnRedHot);
        btnRedHotChiliPeppers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ScriviModalita("2");
                modalita = 2;
                OpenGiocoActivity();
            }
        });

        //Button --> "Civil War"
        btnCivilWar = (Button) findViewById(R.id.btnCiviliWar);
        btnCivilWar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenWheelActivity();
            }
        });
    }
    
    //----------------------------------------------------------------------------------------
    //                                    ALTRE FUNZIONI
    //----------------------------------------------------------------------------------------
    private void OpenDialog() {
        DialogComingSoon dialog = new DialogComingSoon();
        dialog.show(getSupportFragmentManager(), "dialogComingSoon");
    }

    //Torniamo indietro
    private void openPreGiocoActivity() {
        Intent intent = new Intent(this, PreGiocoActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    //Andiamo nel gioco
    private void OpenGiocoActivity() {
        Intent intent = new Intent(this, GiocoActivity.class);
        intent.putExtra("durata", txtDurata.getText().toString());
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    //Andiamo nella Wheel
    private void OpenWheelActivity() {
        Intent intent = new Intent(this, WheelActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    //DEBUGGGGGGG
    private void OpenDialogDebug(String m) {
        DialogDebugging dialog = new DialogDebugging(m);
        dialog.show(getSupportFragmentManager(), "dialogDebug");
    }
}