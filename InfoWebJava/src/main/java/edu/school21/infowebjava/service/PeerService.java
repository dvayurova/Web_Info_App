package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.repository.PeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeerService {

    private final PeerRepository peerRepository;

    @Autowired
    public PeerService(PeerRepository peerRepository) {
        this.peerRepository = peerRepository;
    }

    public List<Peer> getAllPeers(){
        return peerRepository.findAll();
    }

    public Peer addPeer(Peer peer){
        return peerRepository.save(peer);
    }

    public Peer updatePeer(Peer peer){
        return peerRepository.save(peer);
    }

    public void deletePeer(String nickname){
        peerRepository.deleteById(nickname);
    }
}
