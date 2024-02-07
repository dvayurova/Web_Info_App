package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.XP;
import edu.school21.infowebjava.repository.XpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class XpService implements EntityService<XP, Long>{

    private static final Logger logger = LoggerFactory.getLogger(XpService.class);
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
        XP savedXp = xpRepository.save(xp);
        logger.info("new XP with id {} was added", savedXp.getId());
        return savedXp;
    }

    @Override
    public XP update(XP xp){
        XP updatedXp = xpRepository.save(xp);
        logger.info("XP with id {} was updated", updatedXp.getId());
        return updatedXp;
    }

    @Override
    public void delete(Long id){
        xpRepository.deleteById(id);
        logger.info("XP with id{} was deleted", id);

    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "check_id", "xpAmount");
    }
    @Override
    public String tableName(){
        return "XP";
    }
}
