package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.TimeTracker;
import edu.school21.infowebjava.repository.TimeTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TimeTrackerService implements EntityService<TimeTracker, Long>{
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
    public TimeTracker add(TimeTracker timeTracker){
        return timeTrackerRepository.save(timeTracker);
    }

    @Override
    public TimeTracker update(TimeTracker timeTracker){
        return timeTrackerRepository.save(timeTracker);
    }

    @Override
    public void delete(Long id){
        timeTrackerRepository.deleteById(id);
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
