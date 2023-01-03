package com.qbqb.kyubi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogPreGioco extends AppCompatDialogFragment {

    private DialogPreGiocoListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Aspetta!")
            .setMessage("Siete sicuri di iniziare?" +
                    "\nAvete preparato i bicchieri?")
                //NEGATIVO
            .setNegativeButton("quasi...", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) { }
            })
                //POSITIVO
            .setPositiveButton("certo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    listener.onYesClick();
                }

            });
        return builder.create();
    }

    public interface DialogPreGiocoListener{
        void onYesClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (DialogPreGiocoListener) context;
        } catch (ClassCastException e){
            // throw new ClassCastException(context.ToString());
        }

    }
}
