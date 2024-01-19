package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.XP;
import edu.school21.infowebjava.repository.XpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class XpService implements EntityService{
    private final XpRepository xpRepository;

    @Autowired
    public XpService(XpRepository xpRepository) {
        this.xpRepository = xpRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return xpRepository.findAll();
    }

    public XP addXp(XP xp){
        return xpRepository.save(xp);
    }

    public XP updateXp(XP xp){
        return xpRepository.save(xp);
    }

    public void deleteXp(Long id){
        xpRepository.deleteById(id);
    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "check_", "xpAmount");
    }
    @Override
    public String tableName(){
        return "XP";
    }
}