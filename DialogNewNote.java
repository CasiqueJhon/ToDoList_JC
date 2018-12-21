package com.jhonisaac.todolist;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DialogNewNote extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
         //Aqui estoy creando el dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Ahora llamo al metodo inflate para inflar el layout
        //No se crea el inflater porque todas las clases lo tienen
        //Solo lo invoco con el getLayoutInflater()
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //Ahora voy a inflar el layout creado para este Dialog
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);
        //A partir de ahora, dialogView tiene la capacidad de instanciar
        //todos los widgets que tiene mi layout.
        //Tienen que ser final porque todos seran invocados desde una clase anonima.
        final EditText editTitle = dialogView.findViewById(R.id.ediTitle);
        final EditText editDescription = dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = dialogView.findViewById(R.id.cb_idea);
        final CheckBox checkBoxTodo = dialogView.findViewById(R.id.cb_todo);
        final CheckBox checkBoxImportant = dialogView.findViewById(R.id.cb_important);
        //Estos no son final porque no necesitan ser accedidos desde clases anonimas
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnOk = dialogView.findViewById(R.id.btn_ok);

        //Ahora establesco la vista del dialog
        builder.setView(dialogView)
                .setMessage("Add new note");

        //setting the listener to button cancel and OK
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        btnOk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //creating a empty note
                        Note newNote = new Note();

                        //now I call all the setters from
                        //note with method created in Main Activity.
                        newNote.setTitle(editTitle.getText().toString());
                        newNote.setDescription(editDescription.getText().toString());
                        newNote.setIdea(checkBoxIdea.isChecked());
                        newNote.setTodo(checkBoxTodo.isChecked());
                        newNote.setImportant(checkBoxImportant.isChecked());
                        //Hago un casting a MA que es quien ha llamado al Dialog
                        MainActivity callingActivity = (MainActivity) getActivity();
                        //Notifico a la MA que he creado esta nueva nota.
                        callingActivity.createNewNote(newNote);

                        dismiss();
                    }
                }
        );

        //una vez configurada la alerta, le indico al builder que tiene que mostrarla por pantalla
        return builder.create();
    }

}
