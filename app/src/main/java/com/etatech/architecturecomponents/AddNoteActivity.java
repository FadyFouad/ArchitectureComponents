package com.etatech.architecturecomponents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    public static final String TITLE_EXTRAS = "com.etatech.architecturecomponents.EXTRA_TITLE";
    public static final String DESC_EXTRAS = "com.etatech.architecturecomponents.EXTRA_DESC";
    public static final String PRIO_EXTRAS = "com.etatech.architecturecomponents.EXTRA_PRIO";
    private EditText title, desc;
    private NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.edit_txt_title);
        desc = findViewById(R.id.edit_txt_desc);
        picker = findViewById(R.id.num_pick);

        picker.setMinValue(1);
        picker.setMaxValue(100);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote (){
        String sTitle = title.getText().toString();
        String sDesc = desc.getText().toString();

        int iPriorit = picker.getValue();
        if (sTitle.trim().isEmpty()||sDesc.trim().isEmpty()){
            Toast.makeText(this, "Please Enter Title and Desc", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent sendNote = new Intent();
        sendNote.putExtra(TITLE_EXTRAS,sTitle);
        sendNote.putExtra(DESC_EXTRAS,sDesc);
        sendNote.putExtra(PRIO_EXTRAS,iPriorit);

        setResult(RESULT_OK,sendNote);
        finish();
    }
}
