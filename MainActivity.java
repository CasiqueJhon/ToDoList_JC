package com.jhonisaac.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter mNoteAdapter;

    private boolean mSound;
    private int animationOption;
    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteAdapter = new NoteAdapter();
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(mNoteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note tempNote = mNoteAdapter.getItem(position);

                DialogShowNote dialog = new DialogShowNote();
                dialog.sendNoteSelected(tempNote);
                dialog.show(getFragmentManager(), "show_note");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPrefs = getSharedPreferences("To Do List", MODE_PRIVATE);
        mPrefs.getBoolean("sound", true);
        mPrefs.getInt("anim_option" , SettingsActivity.FAST);

    }

    public void createNewNote (Note newNote) {
        mNoteAdapter.addNote(newNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add){
            DialogNewNote dialog = new DialogNewNote();
            dialog.show(getFragmentManager(), "note_create");
        }
        if (item.getItemId() == R.id.settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return false;
    }

    public class NoteAdapter extends BaseAdapter {


        List<Note> mNoteList = new ArrayList<>();


        @Override
        public int getCount() {
            return mNoteList.size();
        }

        @Override
        public Note getItem(int position) {
            return mNoteList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
            }

            ImageView imageViewImportant = view.findViewById(R.id.image_view_important);
            ImageView imageViewTodo = view.findViewById(R.id.image_view_todo);
            ImageView imageViewIdea = view.findViewById(R.id.image_view_idea);

            TextView textViewTitle = view.findViewById(R.id.text_view_title);
            TextView textViewDescription = view.findViewById(R.id.text_view_description);

            Note currentNote = mNoteList.get(position);

            if (!currentNote.isImportant()){
                imageViewImportant.setVisibility(View.GONE);
            }
            if (!currentNote.isTodo()){
                imageViewTodo.setVisibility(View.GONE);
            }
            if (!currentNote.isIdea()) {
                imageViewIdea.setVisibility(View.GONE);
            }

            textViewTitle.setText(currentNote.getTitle());
            textViewDescription.setText(currentNote.getDescription());

            return view;
        }

        public void addNote(Note note) {
            mNoteList.add(note);
            notifyDataSetChanged();
        }
    }
}
