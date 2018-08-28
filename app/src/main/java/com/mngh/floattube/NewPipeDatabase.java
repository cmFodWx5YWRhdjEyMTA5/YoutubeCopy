package com.mngh.floattube;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mngh.floattube.database.AppDatabase;
import com.mngh.floattube.database.Migrations;
import com.mngh.floattube.database.AppDatabase;
import com.mngh.floattube.database.Migrations;

import com.mngh.floattube.database.AppDatabase;

import static com.mngh.floattube.database.AppDatabase.DATABASE_NAME;
import static com.mngh.floattube.database.Migrations.MIGRATION_11_12;

public final class NewPipeDatabase {

    private static volatile AppDatabase databaseInstance;

    private NewPipeDatabase() {
        //no instance
    }

    private static AppDatabase getDatabase(Context context) {
        return Room
                .databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DATABASE_NAME)
                .addMigrations(Migrations.MIGRATION_11_12)
                .fallbackToDestructiveMigration()
                .build();
    }

    @NonNull
    public static AppDatabase getInstance(@NonNull Context context) {
        AppDatabase result = databaseInstance;
        if (result == null) {
            synchronized (NewPipeDatabase.class) {
                result = databaseInstance;
                if (result == null) {
                    databaseInstance = (result = getDatabase(context));
                }
            }
        }

        return result;
    }
}
