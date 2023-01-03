package com.qbqb.kyubi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class DialogDebugging extends AppCompatDialogFragment {

    String prova;

    public DialogDebugging(String m)
    {
        prova = m;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("modalita")
                    .setMessage("->" + prova)
                    //NEGATIVO
                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) { }
                    });
        return builder.create();
    }
}
