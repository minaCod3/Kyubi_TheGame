package com.qbqb.kyubi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogConfermaUscita extends AppCompatDialogFragment {

    private DialogConfermaUscitaListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Aspetta")
                .setMessage("Sei proprio sicuro di voler abbandonare la " +
                        "tua carriera alcolica così?")
                //NEGATIVO
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })

                .setPositiveButton("certo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onYesClick();
                    }

                });
        return builder.create();
    }

    public interface DialogConfermaUscitaListener{
        void onYesClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (DialogConfermaUscitaListener) context;
        } catch (ClassCastException e){ }

    }
}
