package com.qbqb.kyubi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PreGiocoActivity extends AppCompatActivity implements DialogPreGioco.DialogPreGiocoListener {

    //----------------------------------------------------------------------------------------
    //                                    ATTRIBUTI
    //----------------------------------------------------------------------------------------
    Button btnBack;
    Button btnAdd;
    Button btnStart;
    Button btnInfoMale;
    Button btnInfoFemale;
    Button btnEliminaGiocatori;
    TextView linkMina;
    TextView linkDevid;
    EditText txtNomePlayer;
    TextView txtcountM;
    TextView txtcountF;
    RadioButton rdbM;
    RadioButton rdbF;

    public static ArrayList<Giocatore> lstGiocatori = new ArrayList<Giocatore>();
    int countM = 0;
    int countF = 0;


    //----------------------------------------------------------------------------------------
    //                                         MAIN
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_gioco);

        //Button --> "←"
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        //Edit Text
        txtNomePlayer = (EditText) findViewById(R.id.txtNamePlayer);

        //Counter
        txtcountM = (TextView) findViewById(R.id.txtM);
        txtcountF = (TextView) findViewById(R.id.txtF);

        //RadioButton
        rdbM = (RadioButton) findViewById(R.id.rdbMale);
        rdbF = (RadioButton) findViewById(R.id.rdbFemale);

        //Link a Instagram Davide
        linkMina = (TextView) findViewById(R.id.txtInstaMina);
        linkMina.setMovementMethod(LinkMovementMethod.getInstance());
        //Link a Instagram Devid
        linkDevid = (TextView) findViewById(R.id.txtInstaRosa);
        linkDevid.setMovementMethod(LinkMovementMethod.getInstance());

        //Button --> "Elimina Giocatori"
        btnEliminaGiocatori = (Button) findViewById(R.id.btnEliminaGiocatori);
        btnEliminaGiocatori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEliminaActivity();
            }
        });

        //Button --> "+"
        btnAdd = (Button) findViewById(R.id.btnAddPlayer);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux = txtNomePlayer.getText().toString();
                Giocatore player = new Giocatore();
                //Se i dati sono corretti
                if (aux != "" && (rdbF.isChecked() || rdbM.isChecked())) {
                    player.setNome(aux);
                    if (rdbM.isChecked()) {
                        player.setSesso('m');
                        countM ++;
                        txtcountM.setText("" + countM);
                        Toast.makeText(getBaseContext(), "" + player.getNome() + " inserito correttamente", Toast.LENGTH_SHORT).show();
                       // rdbM.setChecked(false);
                    } else {
                        player.setSesso('f');
                        countF ++;
                        txtcountF.setText("" + countF);
                        Toast.makeText(getBaseContext(), "" + player.getNome() + " inserita correttamente", Toast.LENGTH_SHORT).show();
                        //rdbF.setChecked(false);
                    }
                    lstGiocatori.add(player);
                    txtNomePlayer.setText("");
                } else { //Se sbaglia ad inserire
                    OpenDialog2(); //--> glielo facciamo notare
                }
            }
        });

        //Button --> "Go!"
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialog();
            }
        });

        //Button --> "i" male
        btnInfoMale = (Button)findViewById(R.id.btnInfoM);
        btnInfoMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialogPlayer(lstGiocatori, 'm');
            }
        });

        //Button --> "i" female
        btnInfoFemale = (Button)findViewById(R.id.btnInfoF);
        btnInfoFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialogPlayer(lstGiocatori, 'f');
            }
        });

        AggiornaNumGiocatori();
    }

    //----------------------------------------------------------------------------------------
    //                                    ALTRE FUNZIONI
    //----------------------------------------------------------------------------------------
    private void AggiornaNumGiocatori() {
        if(!lstGiocatori.isEmpty()) {
            for (Giocatore g : lstGiocatori) {
                if (g.getSesso() == 'f')
                    countF++;
                if (g.getSesso() == 'm')
                    countM++;
            }
        }
        else {
            countF = 0;
            countM = 0;
        }
        txtcountM.setText("" + countM);
        txtcountF.setText("" + countF);
    }

    //Torniamo indietro
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    //sezione elimina
    private void openEliminaActivity() {
        Intent intent = new Intent(this, EliminaActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    //Chiediamo se vuole veramente iniziare il gioco
    private void OpenDialog() {
        DialogPreGioco dialog = new DialogPreGioco();
        dialog.show(getSupportFragmentManager(), "dialogPregioco");
    }

    @Override
    public void onYesClick() {
        //L'utente ha scelto di proseguire con il gioco
        // salvo la lista dei giocatori in un file.txt
       // scriviGiocatoriSuFile();
        if(countM > 0 && countF > 0){
            Intent intent = new Intent(this, MenuModalitaActivity.class);
            startActivity(intent);
            Animatoo.animateFade(this);
        }
        else{
            Toast.makeText(getBaseContext(), "numero giocatori insufficente", Toast.LENGTH_LONG).show();
        }

    }

    //Scriviamo i giocatori in un file
    private void scriviGiocatoriSuFile() {
        try
        {
            File file = new File("C:\\Users\\Proprietario\\Kyubi\\app\\src\\main\\assets\\player.txt");
            FileWriter scrivi = new FileWriter(file);
            for(Giocatore g : lstGiocatori)
            {
                scrivi.write(g.getSesso());
                scrivi.write(g.getNome());

            }
            scrivi.close();
        }
        catch (Exception e)
        {

        }
    }

    //Chiediamo se vuole veramente iniziare il gioco
    private void OpenDialog2() {
        DialogErroreInserimento dialog = new DialogErroreInserimento();
        dialog.show(getSupportFragmentManager(), "dialogErroreInserimento");
    }

    private void OpenDialogPlayer(ArrayList<Giocatore> lstGiocatori, char s) {
        DialogPlayer dialog = new DialogPlayer(lstGiocatori, s);
        dialog.show(getSupportFragmentManager(), "dialogMale");
    }

    //DEBUGGGGGGG
    private void OpenDialogDebug(String m) {
        DialogDebugging dialog = new DialogDebugging(m);
        dialog.show(getSupportFragmentManager(), "dialogDebug");
    }
}