package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.TransferredPoint;
import edu.school21.infowebjava.repository.TransferredPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransferredPointService implements EntityService<TransferredPoint, Long>{

    private static final Logger logger = LoggerFactory.getLogger(TransferredPointService.class);
    private final TransferredPointRepository transferredPointRepository;

    @Autowired
    public TransferredPointService(TransferredPointRepository transferredPointRepository) {
        this.transferredPointRepository = transferredPointRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return transferredPointRepository.findAll();
    }
    @Override
    public TransferredPoint findById(Long id){
        return transferredPointRepository.findById(id).get();
    }
    @Override
    public TransferredPoint add(TransferredPoint transferredPoint){
        TransferredPoint savedTp = transferredPointRepository.save(transferredPoint);
        logger.info("new TransferredPoint with id {} was added", savedTp.getId());
        return savedTp;
    }

    @Override
    public TransferredPoint update(TransferredPoint transferredPoint){
        TransferredPoint updatedTp = transferredPointRepository.save(transferredPoint);
        logger.info("TransferredPoint with id {} was updated", updatedTp.getId());
        return updatedTp;
    }

    @Override
    public void delete(Long id){
        transferredPointRepository.deleteById(id);
        logger.info("TransferredPoint with id{} was deleted", id);
    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "checkingPeer", "checkedPeer", "pointsAmount");
    }
    @Override
    public String tableName(){
        return "TransferredPoint";
    }
}
