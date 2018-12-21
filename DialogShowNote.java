package com.jhonisaac.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogShowNote extends DialogFragment {
    //variable
    private Note mNote;

    //method
    public void sendNoteSelected (Note noteSelected){
        this.mNote = noteSelected;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);

        TextView txtTitle = dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = dialogView.findViewById(R.id.txtDescription);
        ImageView ivIdea = dialogView.findViewById(R.id.imageViewIdea);
        ImageView ivTodo = dialogView.findViewById(R.id.imageViewTodo);
        ImageView ivImportant = dialogView.findViewById(R.id.imageViewWarning);
        Button btnOK = dialogView.findViewById(R.id.btn_OK);

        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());

        if (!mNote.isIdea()) {
            ivIdea.setVisibility(View.GONE);
        }
        if (!mNote.isTodo()) {
            ivTodo.setVisibility(View.GONE);
        }
        if (!mNote.isImportant()) {
            ivImportant.setVisibility(View.GONE);
        }

        builder.setView(dialogView);

        btnOK.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        return builder.create();

    }

}
