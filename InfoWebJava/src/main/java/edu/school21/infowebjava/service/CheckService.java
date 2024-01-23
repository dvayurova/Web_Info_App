package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.Check;
import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CheckService implements EntityService<Check, Long>{
    private final CheckRepository checkRepository;

    @Autowired
    public CheckService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return checkRepository.findAll();
    }
    @Override
    public Check findById(Long id){
        return checkRepository.findById(id).get();
    }

    @Override
    public Check add(Check check){
        return checkRepository.save(check);
    }

    @Override
    public Check update(Check check){
        return checkRepository.save(check);
    }

    @Override
    public void delete(Long id){
        checkRepository.deleteById(id);
    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "peer", "task", "date");
    }
    @Override
    public String tableName(){
        return "Check";
    }
}

