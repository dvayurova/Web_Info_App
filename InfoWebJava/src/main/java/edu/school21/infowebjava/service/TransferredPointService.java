package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.TransferredPoint;
import edu.school21.infowebjava.repository.TransferredPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TransferredPointService implements EntityService{
    private final TransferredPointRepository transferredPointRepository;

    @Autowired
    public TransferredPointService(TransferredPointRepository transferredPointRepository) {
        this.transferredPointRepository = transferredPointRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return transferredPointRepository.findAll();
    }

    public TransferredPoint addTransferredPoint(TransferredPoint transferredPoint){
        return transferredPointRepository.save(transferredPoint);
    }

    public TransferredPoint updateTransferredPoint(TransferredPoint transferredPoint){
        return transferredPointRepository.save(transferredPoint);
    }

    public void deleteTransferredPoint(Long id){
        transferredPointRepository.deleteById(id);
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
