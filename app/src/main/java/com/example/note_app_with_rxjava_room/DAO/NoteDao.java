package com.example.note_app_with_rxjava_room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.note_app_with_rxjava_room.entity.Note;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface NoteDao {
    @Insert
    Completable insertNote(Note note);

    @Update
    Completable updateNote(Note note);

    @Delete
    Completable deleteNote(Note note);

    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAllNotes();

    @Query("DELETE FROM note_table")
    Completable deleteAllNotes();
}
