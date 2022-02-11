package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {
    private final ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao projectDao)
    {
        mProjectDao = projectDao;
    }

    public LiveData<Project> getProject(long projectId)
    {
        return  mProjectDao.getProject(projectId);
    }

    public void insertProject(Project project)
    {
        mProjectDao.insertProject(project);
    }
    public LiveData<List<Project>> getProjects()
    {
        return mProjectDao.getProjects();
    }
}
