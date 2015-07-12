package com.teamtreehouse.stormy.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import com.teamtreehouse.stormy.R;


public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.error_title)) //change all the text messages to String resources. Steps: 1. Alt+Enter. 2. give the resource a name 3. then hit ESC key when CONTEXT is highlighted in red.
                .setMessage(context.getString(R.string.error_message))
                .setPositiveButton(context.getString(R.string.error_ok_button_text), null);

    AlertDialog dialog  = builder.create();
        return dialog;
    }
}
