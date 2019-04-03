package com.etatech.architecturecomponents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> noteList = new ArrayList<>();
    private Context context ;

    public NoteAdapter(/**List<Note> noteList,**/ Context context) {
//        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item,viewGroup,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {

        Note note = noteList.get(i);
        noteHolder.title.setText(note.getTitle());
        noteHolder.desc.setText(note.getDesc());
        noteHolder.priority.setText(String.valueOf(note.getPriority()));

    }

    public void setNotes(List<Note>notes){
        this.noteList=notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        private TextView title ,desc , priority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            title =itemView.findViewById(R.id.text_title);
            desc =itemView.findViewById(R.id.text_desc);
            priority =itemView.findViewById(R.id.text_priority);
        }
    }
}
