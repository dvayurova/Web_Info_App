package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Task;
import edu.school21.infowebjava.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService implements EntityService{
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return taskRepository.findAll();
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTask(String task){
        taskRepository.deleteById(task);
    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("title", "parentTask", "maxXp");
    }
    @Override
    public String tableName(){
        return "Task";
    }
}
