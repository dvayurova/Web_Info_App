package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Verter;
import edu.school21.infowebjava.repository.VerterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VerterService implements EntityService{
    private final VerterRepository verterRepository;

    @Autowired
    public VerterService(VerterRepository verterRepository) {
        this.verterRepository = verterRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return verterRepository.findAll();
    }

    public Verter addVerter(Verter verter){
        return verterRepository.save(verter);
    }

    public Verter updateVerter(Verter verter){
        return verterRepository.save(verter);
    }

    public void deleteVerter(Long id){
        verterRepository.deleteById(id);
    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "check_", "state", "time");
    }
    @Override
    public String tableName(){
        return "Verter";
    }
}
