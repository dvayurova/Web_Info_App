package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.models.Task;
import edu.school21.infowebjava.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService implements EntityService<Task, String>{
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return taskRepository.findAll();
    }

    @Override
    public Task findById(String task){
        return taskRepository.findById(task).get();
    }

    @Override
    public Task add(Task task){
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task){
        return taskRepository.save(task);
    }

    @Override
    public void delete(String task){
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
