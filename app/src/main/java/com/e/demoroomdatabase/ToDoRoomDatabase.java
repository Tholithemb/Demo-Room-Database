package com.e.demoroomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1)

public abstract class ToDoRoomDatabase extends RoomDatabase {

    public  abstract ToDoDao toDoDao();

    private static volatile ToDoRoomDatabase INSTANCE;

    static ToDoRoomDatabase getInstance(Context context){

        // Singleton pattern
        if (INSTANCE == null){
            synchronized (ToDoRoomDatabase.class){
                if (INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ToDoRoomDatabase.class,"Todo_Database").build();

                }
            }
        }
        return INSTANCE;
    }
}
