package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {
    private final TaskDataRepository mTaskDataSource;
    private final ProjectDataRepository mProjectDataSource;
    private final Executor mExecutor;


    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        mTaskDataSource = taskDataSource;
        mProjectDataSource = projectDataSource;
        mExecutor = executor;
    }


    public void createTask(Task task) {
        mExecutor.execute(() -> mTaskDataSource.createTask(task));
    }

    public void deleteTask(Task task) {
        mExecutor.execute(() -> mTaskDataSource.deleteTask(task));
    }

    public LiveData<List<Task>> getTasks() {
        return mTaskDataSource.getAllTasks();
    }

    public LiveData<List<Project>> getProjects(){
        return mProjectDataSource.getProjects();
    }


}
