package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Verter;
import edu.school21.infowebjava.repository.VerterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VerterService implements EntityService<Verter, Long>{

    private static final Logger logger = LoggerFactory.getLogger(VerterService.class);
    private final VerterRepository verterRepository;

    @Autowired
    public VerterService(VerterRepository verterRepository) {
        this.verterRepository = verterRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return verterRepository.findAll();
    }
    @Override
    public Verter findById(Long id){
        return verterRepository.findById(id).get();
    }
    @Override
    public Verter add(Verter verter){
        Verter savedVerter = verterRepository.save(verter);
        logger.info("new Verter with id {} was added", savedVerter.getId());
        return savedVerter;
    }

    @Override
    public Verter update(Verter verter){
        Verter updatedVerter = verterRepository.save(verter);
        logger.info("Verter with id {} was updated", updatedVerter.getId());
        return updatedVerter;
    }

    @Override
    public void delete(Long id){
        verterRepository.deleteById(id);
        logger.info("Verter with id{} was deleted", id);

    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "check_id", "state", "time");
    }
    @Override
    public String tableName(){
        return "Verter";
    }
}
