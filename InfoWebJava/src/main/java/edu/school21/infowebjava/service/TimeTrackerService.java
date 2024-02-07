package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.TimeTracker;
import edu.school21.infowebjava.repository.TimeTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TimeTrackerService implements EntityService<TimeTracker, Long>{

    private static final Logger logger = LoggerFactory.getLogger(TimeTrackerService.class);
    private final TimeTrackerRepository timeTrackerRepository;

    @Autowired
    public TimeTrackerService(TimeTrackerRepository timeTrackerRepository) {
        this.timeTrackerRepository = timeTrackerRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return timeTrackerRepository.findAll();
    }
    @Override
    public TimeTracker findById(Long id){
        return timeTrackerRepository.findById(id).get();
    }
    @Override
    public TimeTracker add(TimeTracker timeTracker){
        TimeTracker savedTimeTracker = timeTrackerRepository.save(timeTracker);
        logger.info("new TimeTracker with id {} was added", savedTimeTracker.getId());
        return savedTimeTracker;
    }

    @Override
    public TimeTracker update(TimeTracker timeTracker){
        TimeTracker updatedTimeTracker = timeTrackerRepository.save(timeTracker);
        logger.info("TimeTracker with id {} was updated", updatedTimeTracker.getId());
        return updatedTimeTracker;
    }

    @Override
    public void delete(Long id){
        timeTrackerRepository.deleteById(id);
        logger.info("TimeTracker with id{} was deleted", id);
    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "peer", "date", "time", "state");
    }
    @Override
    public String tableName(){
        return "TimeTracker";
    }
}
