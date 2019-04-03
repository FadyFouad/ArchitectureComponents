package com.etatech.architecturecomponents;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {

    private NoteDao noteDao ;
    private LiveData<List<Note>> listLiveData;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao =noteDatabase.noteDao();
        listLiveData = noteDao.getNotes();
    }
    public void insert(Note note ){

        new InsertNoteAsyncTask(noteDao).execute(note);

    }
    public void update(Note note ){

        new UpdateNoteAsyncTask(noteDao).execute(note);

    }
    public void delete(Note note ){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAll(){
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getListLiveData() {
        return listLiveData;
    }

    public static class InsertNoteAsyncTask extends AsyncTask <Note, Void , Void>{

        private NoteDao noteDao ;
        private InsertNoteAsyncTask (NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    public static class UpdateNoteAsyncTask extends AsyncTask <Note, Void , Void>{

        private NoteDao noteDao ;
        private UpdateNoteAsyncTask (NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    public static class DeleteNoteAsyncTask extends AsyncTask <Note, Void , Void>{

        private NoteDao noteDao ;
        private DeleteNoteAsyncTask (NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    public static class DeleteAllNoteAsyncTask extends AsyncTask <Void, Void , Void>{

        private NoteDao noteDao ;
        private DeleteAllNoteAsyncTask (NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
