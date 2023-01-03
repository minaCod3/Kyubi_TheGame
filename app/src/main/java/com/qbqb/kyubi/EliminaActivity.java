package com.qbqb.kyubi;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.LinkedList;
import java.util.List;

public class EliminaActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------
    //                                    ATTRIBUTI
    //----------------------------------------------------------------------------------------
    Button btnElimina;
    Button btnAnnulla;

    ListView lsvMale;
    ListView lsvFemale;
    ArrayList<String> elementiSelezionati;
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
        elementiSelezionati = new ArrayList<>();
            //otteniamo i nomi, maschi e femmine
        arrayMaschi = ottieniGiocatori('m');
        arrayFemmine = ottieniGiocatori('f');

            // Male listView
        final ArrayAdapter adapterM = new ArrayAdapter<String>(this, R.layout.rowmale, R.id.txt_lan,arrayMaschi);
        lsvMale = findViewById(R.id.lsvMale);
        lsvMale.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lsvMale.setAdapter(adapterM);
        lsvMale.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String elementoSelezionato = ((TextView)view).getText().toString();
                if(elementiSelezionati.contains(elementoSelezionato)){
                    elementiSelezionati.remove(elementoSelezionato); //uncheck
                }
                else {
                    elementiSelezionati.add(elementoSelezionato);
                }
            }
        });

            // Female listView
        final ArrayAdapter adapterF = new ArrayAdapter<String>(this, R.layout.rowfemale, R.id.txt_lann, arrayFemmine);
        lsvFemale = findViewById(R.id.lstFemale);
        lsvFemale.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lsvFemale.setAdapter(adapterF);
        lsvFemale.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String elementoSelezionato = ((TextView)view).getText().toString();
                if(elementiSelezionati.contains(elementoSelezionato)){
                    elementiSelezionati.remove(elementoSelezionato); //uncheck
                }
                else {
                    elementiSelezionati.add(elementoSelezionato);
                }
            }
        });


        //------------------------------------------
        //Button --> "Elimina"
        btnElimina = (Button) findViewById(R.id.btnElimina);
        btnElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Elimina();
            //    pG.lstGiocatori = lstGiocatori;
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

        //elimina
    private void Elimina(){
        List<Giocatore> lstDaEliminare = new LinkedList<Giocatore>();
        for(Giocatore g : lstGiocatori){
            for(String n : elementiSelezionati){
                if(g.getNome().equals(n)){
                   // lstGiocatori.remove(g);
                    lstDaEliminare.add(g);
                }
            }
        }
        lstGiocatori.removeAll(lstDaEliminare);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //DEBUGGGGGGG
    private void OpenDialogDebug(String m) {
        DialogDebugging dialog = new DialogDebugging(m);
        dialog.show(getSupportFragmentManager(), "dialogDebug");
    }

}
