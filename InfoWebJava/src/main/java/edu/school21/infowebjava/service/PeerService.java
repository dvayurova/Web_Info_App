package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.repository.PeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PeerService implements EntityService<Peer, String> {

    private final PeerRepository peerRepository;

    @Autowired
    public PeerService(PeerRepository peerRepository) {
        this.peerRepository = peerRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return peerRepository.findAll();
    }
    @Override
    public Peer findById(String nickname){
        return peerRepository.findById(nickname).get();
    }

    @Override
    public Peer add(Peer peer){
        return peerRepository.save(peer);
    }

    @Override
    public Peer update(Peer peer){
        return peerRepository.save(peer) ;
    }

    @Override
    public void delete(String nickname){
        peerRepository.deleteById(nickname);
    }

    @Override
    public List<String> columnNames(){
        return Arrays.asList("nickname", "birthday");
    }

    @Override
    public String tableName(){
        return "Peer";
    }
}
