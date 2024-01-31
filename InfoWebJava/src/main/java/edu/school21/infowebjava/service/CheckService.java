package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.Check;
import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Service
public class CheckService implements EntityService<Check, Long>{
    private final CheckRepository checkRepository;
    private static final Logger logger = LoggerFactory.getLogger(CheckService.class);

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
        Check savedCheck = checkRepository.save(check);
        logger.info("new Check with id {} was added", savedCheck.getId());
        return savedCheck;
    }

    @Override
    public Check update(Check check){
        Check updatedCheck = checkRepository.save(check);
        logger.info("Check with id {} was updated", updatedCheck.getId());
        return updatedCheck;
    }

    @Override
    public void delete(Long id){
        checkRepository.deleteById(id);
        logger.info("Check with id{} was deleted", id);
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

