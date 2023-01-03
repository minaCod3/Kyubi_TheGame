package com.qbqb.kyubi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogErroreInserimento extends AppCompatDialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Attenzione")
                    .setMessage("Sarai mica già ubriaco?" +
                            "\nRicordati di inserire almeno un nome e un sesso!")
                    //NEGATIVO
                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) { }
                    });
            return builder.create();
    }
}
