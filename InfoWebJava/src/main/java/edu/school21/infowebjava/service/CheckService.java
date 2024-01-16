package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.Check;
import edu.school21.infowebjava.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckService {
    private final CheckRepository checkRepository;

    @Autowired
    public CheckService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    public List<Check> getAllChecks(){
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
}
