package com.pedrodeveloper14.ifmsaelsalvador.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pedrodeveloper14.ifmsaelsalvador.database.daos.CommitteeDao;
import com.pedrodeveloper14.ifmsaelsalvador.database.daos.ProjectDao;
import com.pedrodeveloper14.ifmsaelsalvador.database.daos.UserDao;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Committee;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.Project;
import com.pedrodeveloper14.ifmsaelsalvador.database.models.User;

@Database(entities = {Project.class, Committee.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    private static volatile AppDB instance;
    private static final String NAME = "ifmsa.db";

    public static AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDB.class, NAME)
                    .build();
        }
        return instance;
    }

    public abstract ProjectDao projectDao();

    public abstract CommitteeDao committeeDao();

    public abstract UserDao userDao();
}
