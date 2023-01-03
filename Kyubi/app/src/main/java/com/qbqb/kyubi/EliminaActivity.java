package com.qbqb.kyubi;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;

public class EliminaActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------
    //                                    ATTRIBUTI
    //----------------------------------------------------------------------------------------
    Button btnElimina;
    Button btnAnnulla;

    ArrayList<Giocatore> lstGiocatori;
    String[] arrayMaschi; //
    String[] arrayFemmine; //
    //----------------------------------------------------------------------------------------
    //                                      MAIN
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimina);

        //Giocatori---------------------------------
        PreGiocoActivity pG = new PreGiocoActivity();
        lstGiocatori = pG.lstGiocatori;
            //otteniamo i nomi, maschi e femmine
        arrayMaschi = ottieniGiocatori('m');
        arrayFemmine = ottieniGiocatori('f');


        //------------------------------------------
        //Button --> "Elimina"
        btnElimina = (Button) findViewById(R.id.btnElimina);
        btnElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // elimina giocatori
                openPreGiocoActivity();
            }
        });

        //Button --> "Annulla"
        btnAnnulla = (Button) findViewById(R.id.btnAnnulla);
        btnAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreGiocoActivity();
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

        // carichiamo i vettori con i nomi dei giocatori
    private String[] ottieniGiocatori(Character sesso){
        ArrayList<String> lstAux = new ArrayList<>();

        for(Giocatore g : lstGiocatori){
            if(sesso == 'm'){ // vogliamo i maschi
                if(g.getSesso() == 'm'){ // otteniamo i maschi
                    lstAux.add(g.getNome());
                }
            }
            else { // vogliamo le femmene
                if(g.getSesso() == 'f'){ // otteniamo a femmena
                    lstAux.add(g.getNome());
                }
            }
        }

        String[] elenco = lstAux.toArray(new String[0]);
        return elenco;
    }

    //DEBUGGGGGGG
    private void OpenDialogDebug(String m) {
        DialogDebugging dialog = new DialogDebugging(m);
        dialog.show(getSupportFragmentManager(), "dialogDebug");
    }


}
