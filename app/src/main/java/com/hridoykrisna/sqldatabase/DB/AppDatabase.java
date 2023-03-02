package com.hridoykrisna.sqldatabase.DB;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version  = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UserDao userDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDBInstance(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "my_name.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
