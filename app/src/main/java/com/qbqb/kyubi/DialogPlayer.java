package com.qbqb.kyubi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class DialogPlayer extends AppCompatDialogFragment {

    ArrayList<Giocatore> lstGiocatori;
    char sex;

    public DialogPlayer(ArrayList<Giocatore> lst, char s)
    {
        lstGiocatori = lst;
        sex = s;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(sex == 'm'){
            builder.setTitle("Ragazzi")
                    .setMessage(getLstRagazzi())
                    //NEGATIVO
                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) { }
                    });
        }
        else{
            builder.setTitle("Ragazze")
                    .setMessage(getLstRagazze())
                    //NEGATIVO
                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) { }
                    });
        }
        return builder.create();
    }

    private String getLstRagazzi() {
        String aux = "";
        for (Giocatore g: lstGiocatori) {
            if(g.getSesso() == 'm')
                aux += "\n" + g.getNome();
        }
        return aux;
    }

    private String getLstRagazze() {
        String aux = "";
        for (Giocatore g: lstGiocatori) {
            if(g.getSesso() == 'f')
                aux += "\n" + g.getNome();
        }
        return aux;
    }
}
