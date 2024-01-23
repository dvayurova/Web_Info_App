package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.models.XP;
import edu.school21.infowebjava.repository.XpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class XpService implements EntityService<XP, Long>{
    private final XpRepository xpRepository;

    @Autowired
    public XpService(XpRepository xpRepository) {
        this.xpRepository = xpRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return xpRepository.findAll();
    }
    @Override
    public XP findById(Long id){
        return xpRepository.findById(id).get();
    }
    @Override
    public XP add(XP xp){
        return xpRepository.save(xp);
    }

    @Override
    public XP update(XP xp){
        return xpRepository.save(xp);
    }

    @Override
    public void delete(Long id){
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
