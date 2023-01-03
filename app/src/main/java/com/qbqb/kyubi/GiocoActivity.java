package com.qbqb.kyubi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.io.*;

public class GiocoActivity extends AppCompatActivity implements DialogConfermaUscita.DialogConfermaUscitaListener{

    //----------------------------------------------------------------------------------------
    //                                    ATTRIBUTI
    //----------------------------------------------------------------------------------------
    Button btnAvanti;
    Button btnExit;
    ArrayList<Giocatore> lstGiocatori;
    TextView txtTestoDomanda;
    View viu;
    TextView txtTitoloDomanda;
    TextView txtFineJolly;

    int valueGioco = 0;
    int countTurni = 1;
    int totTurniLMS = 41;
    final int totTurniRHCP = 46;
    int numTurni;
    int numTurniRHCP;
    static Boolean spiaze;
    String durata;

    // VARIABILI LAST MAN STANDING
    final int numBase = 71;
    final int numGioco = 29;
    final int numTest = 4;
    final int numPiccante = 25;
    final int numVirus = 29;
    final int numGoccia = 10;

    int[] lstDomBase = new int[numBase];
    int[] lstDomGioco = new int[numGioco];
    int[] lstDomTest = new int[numTest];
    int[] lstDomPiccante = new int[numPiccante];
    int[] lstDomVirus = new int[numVirus];
    int[] lstDomGoccia = new int[numGoccia];

    // VARIABILI RED HOT CHILI PEPPERS
    final int numChill = 26;
    final int numMaliziose = 25;
    final int numPiccanteRH = 20;
    final int numRedHot = 5;
    final int numBottle = 1;

    int[] lstDomChill = new int[numChill];
    int[] lstDomMaliziose = new int[numMaliziose];
    int[] lstDomPiccanteRH = new int[numPiccanteRH];
    int[] lstDomRedHot = new int[numRedHot];
    int[] lstDomBottle = new int[numBottle];

    ArrayList<fineJolly> contaJolly = new ArrayList<>();
    class fineJolly{
        int contatore;
        String testo;

        public fineJolly(String b)
        {
            contatore = 8;
            testo = b;
        }

        public int getContaJolly()
        {
            return contatore;
        }


        public void decrementa()
        {
            contatore --;
        }
    };


    //----------------------------------------------------------------------------------------
    //                                      MAIN
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioco);

            //***************************************************
            //             dichiarazione button e altro
            //***************************************************
        //Button -> "X" per tornare alla schermata iniziale
        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenConfermaUscitaDialog();
            }
        });

        //Button -> "" per skippare la domanda
        btnAvanti = (Button) findViewById(R.id.btnAvanti);

        //TextView -> Testo Domanda
        txtTestoDomanda = (TextView)findViewById(R.id.txtTestoDomanda);
        txtTestoDomanda.setMovementMethod(new ScrollingMovementMethod());

        txtTitoloDomanda = (TextView)findViewById(R.id.txtTitoloDomanda);
        txtFineJolly = (TextView) findViewById(R.id.txtFineJolly);

        //View
        viu = (View) findViewById(R.id.view);

            //***************************************************
            //              dati pre gioco
            //***************************************************
        //breve, normale, lunga
        Bundle extra = getIntent().getExtras();
        if(extra!=null){
            durata = extra.getString("durata");
            switch(durata){
                case "easy":numTurni = 26; numTurniRHCP = 31;break;
                case "normal":numTurni = 41; numTurniRHCP = 45;break;
                case "hard":numTurni =  61; numTurniRHCP = 47;break;
            }
        }



        //All'inizio leggiamo la modalità
        MenuModalitaActivity mm = new MenuModalitaActivity();
        valueGioco = mm.modalita;

        PreGiocoActivity pG = new PreGiocoActivity();
        lstGiocatori = pG.lstGiocatori;

        InizializzaVettori(lstDomBase, numBase);
        InizializzaVettori(lstDomGioco, numGioco);
        InizializzaVettori(lstDomTest, numTest);
        InizializzaVettori(lstDomVirus, numVirus);
        InizializzaVettori(lstDomGoccia, numGoccia);
        InizializzaVettori(lstDomPiccante, numPiccante);

        InizializzaVettori(lstDomBottle, numBottle);
        InizializzaVettori(lstDomChill, numChill);
        InizializzaVettori(lstDomMaliziose, numMaliziose);
        InizializzaVettori(lstDomPiccanteRH, numPiccanteRH);
        InizializzaVettori(lstDomRedHot, numRedHot);



            //***************************************************
            //                  loop gioco
            //***************************************************

        switch(valueGioco) {
            case 1: try {
                CalcoloTipoDomandaLMS();
            } catch (IOException e) {  }  break;
            case 2:
                try {
                    CalcoloTipoDomandaRHCP(countTurni);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3: ; break;
            default:
        }


        btnAvanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(valueGioco) {
                    case 1: LastManStanding(); break;
                    case 2: RedHotChiliPeppers();
                        break;
                    case 3: ; break;
                    default:
                }
            }
        });
    }




    //----------------------------------------------------------------------------------------
    //                                    ALTRE FUNZIONI
    //----------------------------------------------------------------------------------------

    //MODALITA LAST MAN STANDING
    private void LastManStanding() {
        boolean hope = true;
        int bigHope;
        countTurni ++;

        for(fineJolly f : contaJolly){
            f.decrementa();
            if(f.getContaJolly() == 0) {
                txtFineJolly.setVisibility(View.VISIBLE);
                txtTestoDomanda.setText(f.testo);
                txtTitoloDomanda.setText("Jolly");
                viu.setBackgroundColor(Color.parseColor("#ee82ee"));
                countTurni--;
                hope = false;
                break;
            }


            /*
            if(f.getContaJolly() == 0){
                txtFineJolly.setVisibility(View.VISIBLE);
                txtTestoDomanda.setText(f.testo);
                txtTitoloDomanda.setText("Jolly");
                viu.setBackgroundColor(Color.parseColor("#ee82ee"));
                countTurni --;
                hope = false;
            }else{
                f.decrementa();
            }*/
        }

        if(hope) {
            txtFineJolly.setVisibility(View.INVISIBLE);
            if (countTurni < numTurni) {
                if (countTurni % 20 == 0 && (numTurni - 1) % 20 == 0) {
                    //domande test
                    try {
                        txtTitoloDomanda.setText("Test");
                        viu.setBackgroundColor(Color.parseColor("#32cd32"));
                        txtTestoDomanda.setText(testoQuesito(ottieniDomandaGrezzaDaFile("test.txt", numTest, lstDomTest)));
                    } catch (IOException e) {
                    }
                } else if (countTurni % 25 == 0 && (numTurni - 1) % 25 == 0) {
                    try {
                        txtTitoloDomanda.setText("Test");
                        viu.setBackgroundColor(Color.parseColor("#32cd32"));
                        txtTestoDomanda.setText(testoQuesito(ottieniDomandaGrezzaDaFile("test.txt", numTest, lstDomTest)));
                    } catch (IOException e) {
                    }
                } else {
                    try {
                        CalcoloTipoDomandaLMS();
                    } catch (IOException e) {
                    }
                }
            } else {
                //FINE
                txtTitoloDomanda.setTextColor(Color.RED);
                txtTitoloDomanda.setText("FINE");
                txtTestoDomanda.setText("");
                viu.setBackgroundColor(Color.WHITE);

                int i = 1000000;
                int rand, randlavendetta, randilritorno;
                while (i != 0) {
                    rand = (int) (Math.random() * 999);
                    randlavendetta = (int) (Math.random() * 999);
                    randilritorno = (int) (Math.random() * 9999);
                    // rand = rand%randlavendetta;
                    i--;
                }
                Intent intent = new Intent(this, MenuModalitaActivity.class);
                startActivity(intent);
                Animatoo.animateFade(this);
                finish();
            }
        }else{
            contaJolly.remove(0);
        }
    }

    //MODALITA RED HOT CHILI PEPPERS
    private void RedHotChiliPeppers() {
        countTurni ++;
        if(countTurni < numTurniRHCP) {
            try {
                CalcoloTipoDomandaRHCP(countTurni);
            }
            catch(IOException e) {}
        }
        else{
            //FINE
            txtTitoloDomanda.setTextColor(Color.RED);
            txtTitoloDomanda.setText("FINE");
            txtTestoDomanda.setText("");
            viu.setBackgroundColor(Color.WHITE);


            Intent intent = new Intent(this, MenuModalitaActivity.class);
            startActivity(intent);
            Animatoo.animateFade(this);
            finish();
        }
    }

    private void CalcoloTipoDomandaRHCP(int count) throws IOException {
        int tipoDomanda = 0;
        int aux;
        String help = "";

        if (count < 16)
        {   aux = (int) (Math.random() * 100); //da 1 a 100
            if(aux <51 && aux > 0)
                tipoDomanda = 1;
            if(aux > 50 && aux < 61)
                tipoDomanda = 6; // normale
            else if(aux > 60 && aux < 76)
                tipoDomanda = 7; // jolly
            else if(aux > 75 && aux < 91)
                tipoDomanda = 8; // gioco
            else if(aux > 90 && aux < 101)
                tipoDomanda = 9; // goccia
        }
        else if (count == 30) // bottle game
            tipoDomanda = 5;
        else if (count > 15 && count < 30)
        {   aux = (int) (Math.random() * 100); //da 1 a 100
            if(aux <51 && aux > 0)
                tipoDomanda = 2;
            if(aux > 50 && aux < 61)
                tipoDomanda = 6; // normale
            else if(aux > 60 && aux < 76)
                tipoDomanda = 7; // jolly
            else if(aux > 75 && aux < 91)
                tipoDomanda = 8; // gioco
            else if(aux > 90 && aux < 101)
                tipoDomanda = 9; // goccia
        }
        else if(count > 30 && count < 45)
        {   aux = (int) (Math.random() * 100); //da 1 a 100
            if(aux <51 && aux > 0)
                tipoDomanda = 3;
            if(aux > 50 && aux < 61)
                tipoDomanda = 6; // normale
            else if(aux > 60 && aux < 76)
                tipoDomanda = 7; // jolly
            else if(aux > 75 && aux < 91)
                tipoDomanda = 8; // gioco
            else if(aux > 90 && aux < 101)
                tipoDomanda = 9; // goccia
        }
        else
            tipoDomanda = 4; //penze

        /*if (count < 11)
            tipoDomanda = 1;
        else if (count > 10 && count < 20) {//85% soft
            aux = (int) (Math.random() * 100); //da 1 a 100
            if(aux < 91)
                tipoDomanda = 2;
            else
                tipoDomanda = 3;
        }
        else if(count == 20) //turno speciale
            tipoDomanda = 5;
        else if(count > 20 && count < 29) { //25% soft
            aux = (int) (Math.random() * 100); //da 1 a 100
            if (aux < 81)
                tipoDomanda = 3;
            else
                tipoDomanda = 2;
        }
        else
            tipoDomanda = 4; //penze*/

        switch(tipoDomanda){
            //Chill --> azzurro OK
            case 1: viu.setBackgroundColor(Color.parseColor("#0AC0F7"));
                txtTitoloDomanda.setText("Chill");
                help = ottieniDomandaGrezzaDaFile("chill.txt", numChill, lstDomChill);break;
            //Maliziosa --> rosa OK
            case 2: viu.setBackgroundColor(Color.parseColor("#F780EF"));
                txtTitoloDomanda.setText("Maliziosa");
                help = ottieniDomandaGrezzaDaFile("malizioso.txt", numMaliziose, lstDomMaliziose);break;
            //Piccante --> rosso OK
            case 3: viu.setBackgroundColor(Color.RED);
                txtTitoloDomanda.setText("Piccante");
                help = ottieniDomandaGrezzaDaFile("piccanteRH.txt", numPiccanteRH, lstDomPiccanteRH);break;
            //RedHot --> viola OK
            case 4: viu.setBackgroundColor(Color.parseColor("#D003F4"));
                txtTitoloDomanda.setText("Red Hot");
                help = ottieniDomandaGrezzaDaFile("redhot.txt", numRedHot, lstDomRedHot);break;
            //Bottle game --> verde OK
            case 5: viu.setBackgroundColor(Color.parseColor("#30CFD0"));
                txtTitoloDomanda.setText("Bottle Game");
                help = ottieniDomandaGrezzaDaFile("bottle.txt", numBottle, lstDomBottle);
                break;
                // Normale
            case 6: viu.setBackgroundColor(Color.parseColor("#005BEA"));
                txtTitoloDomanda.setText("");
                help = ottieniDomandaGrezzaDaFile("base.txt", numBase, lstDomBase);break;
            //Gioco --> arancione OK
            case 8: viu.setBackgroundColor(Color.parseColor("#FDA085")); //"#fda085"
                txtTitoloDomanda.setText("Gioco");
                help = ottieniDomandaGrezzaDaFile("gioco.txt", numGioco, lstDomGioco);break;
            //Jolly --> giallo
            case 7: viu.setBackgroundColor(Color.parseColor("#ee82ee")); //"#fee140"
                txtTitoloDomanda.setText("Jolly");
                help = ottieniDomandaGrezzaDaFile("virus.txt", numVirus, lstDomVirus);break;
            //Goccia --> blu OK
            case 9: viu.setBackgroundColor(Color.parseColor("#30CFD0")); //"#30cfd0"
                txtTitoloDomanda.setText("Goccia");
                help = ottieniDomandaGrezzaDaFile("goccia.txt", numGoccia, lstDomGoccia);break;
            default:
        }
        txtTestoDomanda.setText(testoQuesito(help));
    }

    //Funzione che trasforma gli eventuali % in giocatori e ritorna la domanda
    private String testoQuesito(String t) {
        String text = t;
        String nomeGiocatore;
        char[] domanda = text.toCharArray();
        int j = 0;
        int lungStr = text.length();
// % primo --- £ secondo ------ $ maschi ----- = femmine
        String definitiva[];
        for(int i = 0; i < lungStr; i ++) {
            switch(domanda[i]) {
                case '%':
                    nomeGiocatore = ottieniGiocatore(3);
                    definitiva = text.split("%");
                    try {
                        text = definitiva[j] + nomeGiocatore + definitiva[j + 1];
                    }
                    catch (Exception e){}
                    break;
                case '£':
                    nomeGiocatore = ottieniGiocatore(3);
                    definitiva = text.split("£");
                    try {
                        text = definitiva[j] + nomeGiocatore + definitiva[j + 1];
                    }
                    catch (Exception e){}
                    break;
                case '€':
                    nomeGiocatore = ottieniGiocatore(1);
                    definitiva = text.split("€");
                    try {
                        text = definitiva[j] + nomeGiocatore + definitiva[j + 1];
                    }
                    catch (Exception e){}
                    break;
                case '=':
                    nomeGiocatore = ottieniGiocatore(2);
                    definitiva = text.split("=");
                    try {
                        text = definitiva[j] + nomeGiocatore + definitiva[j + 1];
                    }
                    catch (Exception e){}
                    break;
                default:
            }
        }
        AggiornaOccupazioneGiocatori();
        return text;
    }

    //rendaimoli tutti liberi ancora
    private void AggiornaOccupazioneGiocatori() {
        for(Giocatore g : lstGiocatori){
            g.setOccupato(false);
        }
    }


    //CalcoloTipoDomanda
    private void CalcoloTipoDomandaLMS() throws IOException {
        // i numeri da 1 a 5  sono -> Base, Gioco, Piccante, Goccia, Jolly
        int tipoDomanda = 0;
        int aux = (int) (Math.random() * 100); //da 1 a 100
        String help = "";

        if(aux < 50 && aux > 0)
            tipoDomanda = 1;
        else if(aux > 49 && aux < 65)
            tipoDomanda = 2;
        else if(aux > 64 && aux < 80)
            tipoDomanda = 3;
        else if(aux > 79 && aux < 95)
            tipoDomanda = 4;
        else if(aux > 94 && aux < 101)
            tipoDomanda = 5;

        switch(tipoDomanda){
            //base --> azzurrino OK
            case 1: viu.setBackgroundColor(Color.parseColor("#005BEA"));
                txtTitoloDomanda.setText("");
                    help = ottieniDomandaGrezzaDaFile("base.txt", numBase, lstDomBase);break;
            //Gioco --> arancione OK
            case 2: viu.setBackgroundColor(Color.parseColor("#FDA085")); //"#fda085"
                txtTitoloDomanda.setText("Gioco");
                    help = ottieniDomandaGrezzaDaFile("gioco.txt", numGioco, lstDomGioco);break;
            //Piccante --> rosa/fucsia OK
            case 3: viu.setBackgroundColor(Color.RED/*parseColor("8B0000")*/); //"#f093fb"
                txtTitoloDomanda.setText("Piccante");
                    help = ottieniDomandaGrezzaDaFile("piccante.txt", numPiccante, lstDomPiccante);break;
            //Jolly --> giallo
            case 4: viu.setBackgroundColor(Color.parseColor("#ee82ee")); //"#fee140"
                txtTitoloDomanda.setText("Jolly");
                    help = ottieniDomandaGrezzaDaFile("virus.txt", numVirus, lstDomVirus);break;
            //Goccia --> blu OK
            case 5: viu.setBackgroundColor(Color.parseColor("#30CFD0")); //"#30cfd0"
                txtTitoloDomanda.setText("Goccia");
                    help = ottieniDomandaGrezzaDaFile("goccia.txt", numGoccia, lstDomGoccia);break;
            default:
        }
        String testoFinito = testoQuesito(help);
        txtTestoDomanda.setText(testoFinito);
        if(tipoDomanda == 4){
            fineJolly f = new fineJolly(testoFinito);
            contaJolly.add(f);
        }
    }

    private String ottieniDomandaGrezzaDaFile(String nomeFile, int numDomande, int[] lst) throws IOException {

        String domandaGrezza = "error, question not found";
        InputStream fin = getAssets().open(nomeFile);
        String line;
        int rand;
        int countRiga = 0;
        int i = 0;
        boolean controllo = false;
        rand = (int)(Math.random() * (numDomande));
        do {
           // rand = (int)(Math.random() * (numDomande));
            if(lst[i] == 333)
                controllo = true;
            else if (lst[i] == rand) {
                i = 0;
                rand = (int)(Math.random() * (numDomande));
            }
            else
                i++;
        }while(!controllo);
        lst[i] = rand;
       // if(fin != null){
            InputStreamReader inputReader = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            while ((line = bufferedReader.readLine()) != null){
                if(countRiga == (rand))
                    domandaGrezza = line;
                countRiga ++;
            }
            fin.close();
      //  }
        return domandaGrezza;
    }

    //otteniamo il giocatore
    private String ottieniGiocatore(int i) {

        String nome;
        int rand;
        String aux;
        ArrayList<String>lstProvvisoria = new ArrayList<String>();

        switch(i) {
            case 1: //maschio
                for(Giocatore g : lstGiocatori) {
                    if(g.getSesso() == 'm' && g.getOccupato() == false)
                        lstProvvisoria.add(g.getNome());
                }
                rand = (int) (Math.random() * lstProvvisoria.size());
                aux = lstProvvisoria.get(rand);
                for(Giocatore g : lstGiocatori){
                    if(aux == g.getNome())
                        g.setOccupato(true);
                }
                break;
            case 2: //femmina
                for(Giocatore g : lstGiocatori) {
                    if(g.getSesso() == 'f' && g.getOccupato() == false)
                        lstProvvisoria.add(g.getNome());
                }
                rand = (int) (Math.random() * lstProvvisoria.size());
                aux = lstProvvisoria.get(rand);
                for(Giocatore g : lstGiocatori){
                    if(aux == g.getNome())
                        g.setOccupato(true);
                }
                ;break;
            case 3: //uguale
                for(Giocatore g : lstGiocatori) {
                    if(g.getOccupato() == false)
                        lstProvvisoria.add(g.getNome());
                }
                rand = (int) (Math.random() * lstProvvisoria.size());
                aux = lstProvvisoria.get(rand);
                for(Giocatore g : lstGiocatori){
                    if(aux == g.getNome())
                        g.setOccupato(true);
                }
                ;break;
            default: return "";
        }
        return aux;
    }
//

//
    //Chiediamo se vuole veramente uscire
    private void OpenConfermaUscitaDialog() {
        DialogConfermaUscita dialog = new DialogConfermaUscita();
        dialog.show(getSupportFragmentManager(), "dialogConfermaUscita");
    }

    @Override
    public void onYesClick() {
        //L'utente ha scelto di uscire dal gioco
        Intent intent = new Intent(this, MenuModalitaActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
       // finish ();
        //System.exit(0);
    }

    //DEBUGGGGGGG
    private void OpenDialogDebug(String m) {
        DialogDebugging dialog = new DialogDebugging(m);
        dialog.show(getSupportFragmentManager(), "dialogDebug");
    }

    private void OpenDialogFine() {
        DialogFineGioco dialog = new DialogFineGioco();
        dialog.show(getSupportFragmentManager(), "dialogFineGioco");

        Intent intent = new Intent(this, MenuModalitaActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);

    }

    //MODALITA CIVIL WAR
    private void CivilWar() {
        //do while che durerà per l'intera durata del gioco
        do {
            countTurni ++;
            if(countTurni % 10 == 0) {
                //domande test
            }
            else {
                //otteniamo il tipo di domanda da una funzione semi randomica (e cambiamo lo sfondo)
                // ritorniamo il valore in Stringa della domanda da un'altra funzione
                //e lo applicchiamo alla textView
            }
        }while(btnAvanti.performClick() && countTurni < totTurniLMS);
    }

    private void InizializzaVettori(int[] vet, int num) {
        for(int i = 0; i < num; i++) {
            vet[i] = 333;
        }
    }

}
