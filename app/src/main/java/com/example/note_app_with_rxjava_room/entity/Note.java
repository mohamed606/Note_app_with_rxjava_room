package com.example.note_app_with_rxjava_room.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "note_table")
public class Note implements Parcelable {

    private int priority;

    private String title;

    private String body;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Note(int priority, String title, String body) {
        this.priority = priority;
        this.title = title;
        this.body = body;
        this.id = id;
    }

    protected Note(Parcel in) {
        priority = in.readInt();
        title = in.readString();
        body = in.readString();
        id = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return priority == note.priority &&
                id == note.id &&
                Objects.equals(title, note.title) &&
                Objects.equals(body, note.body);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(priority);
        dest.writeString(title);
        dest.writeString(body);
        dest.writeInt(id);
    }
}
