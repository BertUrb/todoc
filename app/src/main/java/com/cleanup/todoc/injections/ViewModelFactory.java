package com.cleanup.todoc.injections;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.database.SaveMyTaskDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private static ViewModelFactory sModelFactory;
    private final TaskDataRepository mTaskDataRepository;
    private final ProjectDataRepository mProjectDataRepository;
    private final Executor mExecutor;

    private ViewModelFactory(Context context) {
        SaveMyTaskDatabase db = SaveMyTaskDatabase.getInstance(context);
        mTaskDataRepository = new TaskDataRepository(db.taskDao());
        mProjectDataRepository = new ProjectDataRepository(db.projectDao());
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory getInstance(Context context) {
        if (sModelFactory == null) {
            synchronized (ViewModelFactory.class) {
                if (sModelFactory == null) {
                    sModelFactory = new ViewModelFactory(context);
                }
            }
        }
        return sModelFactory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {

            return (T) new TaskViewModel(mTaskDataRepository, mProjectDataRepository, mExecutor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}

