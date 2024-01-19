package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.Check;
import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CheckService implements EntityService{
    private final CheckRepository checkRepository;

    @Autowired
    public CheckService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    public List<? extends EntityInterface> getAll(){
        return checkRepository.findAll();
    }

    public Check addCheck(Check check){
        return checkRepository.save(check);
    }

    public Check updateCheck(Check check){
        return checkRepository.save(check);
    }

    public void deleteCheck(Long id){
        checkRepository.deleteById(id);
    }
    public List<String> columnNames(){
        return Arrays.asList("id", "peer", "task", "date");
    }
    public String tableName(){
        return "Check";
    }
}

