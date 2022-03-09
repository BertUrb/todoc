package com.cleanup.todoc.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.concurrent.Executors;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class SaveMyTaskDatabase extends RoomDatabase {

    private static volatile SaveMyTaskDatabase INSTANCE;

    public static SaveMyTaskDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SaveMyTaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaveMyTaskDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
              Project[] projectList = Project.getAllProjects();
                for (Project project: projectList) {
                    Executors.newSingleThreadExecutor().execute(
                            () -> INSTANCE.projectDao().insertProject(project)
                    );
                }
                super.onCreate(db);
            }

        };
    }
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();
}
