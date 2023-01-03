package com.qbqb.kyubi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class DialogFineGioco extends AppCompatDialogFragment{

   // private DialogFineGioco.DialogFineGiocoListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Fine")
                .setMessage("Gioco finito! E ora di provare le altre modalità!")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                  //      listener.onClick();
                    }

                });
        return builder.create();
    }

/*
    public interface DialogFineGiocoListener{
        void onClick();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (DialogFineGioco.DialogFineGiocoListener) context;
        } catch (ClassCastException e){ }

    }*/
}