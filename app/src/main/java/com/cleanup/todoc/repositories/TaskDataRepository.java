package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {
    private final TaskDao mTaskDao;

    public TaskDataRepository(TaskDao taskDao)
    {
        mTaskDao = taskDao;
    }

    public LiveData<List<Task>> getAllTasks()
    {
        return mTaskDao.getAllTasks();
    }


    public long createTask(Task task)
    {
        return mTaskDao.createTask(task);
    }

    public long deleteTask(Task task)
    {
        return mTaskDao.deleteTask(task);
    }

    public long deleteAll() { return mTaskDao.deleteAll();}


}
