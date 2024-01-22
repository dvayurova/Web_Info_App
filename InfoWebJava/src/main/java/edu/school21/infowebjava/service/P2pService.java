package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.P2P;
import edu.school21.infowebjava.repository.P2pRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class P2pService implements EntityService<P2P, Long>{
    private final P2pRepository p2pRepository;

    public P2pService(P2pRepository p2pRepository) {
        this.p2pRepository = p2pRepository;
    }

    public List<? extends EntityInterface> getAll(){
        return p2pRepository.findAll();
    }
    @Override
    public P2P add(P2P p2p) {return p2pRepository.save(p2p);}
    @Override
    public P2P update(P2P p2p) {return p2pRepository.save(p2p);}
    @Override
    public void delete(Long id) {
        p2pRepository.deleteById(id);
    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "check_", "checkingPeer", "state", "time");
    }
    @Override
    public String tableName(){
        return "P2P";
    }

    public P2P findById(Long id) {return p2pRepository.findById(id).get();}
}
