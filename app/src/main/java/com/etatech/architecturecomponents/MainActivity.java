package com.etatech.architecturecomponents;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    private NoteViewModel viewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton actionButton = findViewById(R.id.btn_add_note);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,AddNoteActivity.class),
                        ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView =findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter =new NoteAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        viewModel.getData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==ADD_NOTE_REQUEST&&resultCode==RESULT_OK){
            String title = data.getStringExtra(AddNoteActivity.TITLE_EXTRAS);
            String desc = data.getStringExtra(AddNoteActivity.DESC_EXTRAS);
            int prio = data.getIntExtra(AddNoteActivity.PRIO_EXTRAS,1);

            Note note = new Note(title,desc,prio);
            viewModel.isert(note);
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
