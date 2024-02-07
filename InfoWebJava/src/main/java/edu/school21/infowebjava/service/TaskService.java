package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Task;
import edu.school21.infowebjava.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskService implements EntityService<Task, String>{

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
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
        Task savedTask = taskRepository.save(task);
        logger.info("new Task with title {} was added", savedTask.getTitle());
        return savedTask;
    }

    @Override
    public Task update(Task task){
        Task updatedTask = taskRepository.save(task);
        logger.info("Task with title {} was updated", updatedTask.getTitle());
        return updatedTask;
    }

    @Override
    public void delete(String task){
        taskRepository.deleteById(task);
        logger.info("Task with title {} was deleted", task);
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
