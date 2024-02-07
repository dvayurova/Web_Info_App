package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.repository.PeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PeerService implements EntityService<Peer, String> {

    private static final Logger logger = LoggerFactory.getLogger(PeerService.class);

    private final PeerRepository peerRepository;

    @Autowired
    public PeerService(PeerRepository peerRepository) {
        this.peerRepository = peerRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll() {
        return peerRepository.findAll();
    }

    @Override
    public Peer findById(String nickname) {
        return peerRepository.findById(nickname).get();
    }

    @Override
    public Peer add(Peer peer) {
        Peer savedPeer = peerRepository.save(peer);
        logger.info("new Peer with nickname {} was added", savedPeer.getNickname());
        return savedPeer;
    }

    @Override
    public Peer update(Peer peer) {
        Peer updatedPeer = peerRepository.save(peer);
        logger.info("Peer with nickname {} was updated", updatedPeer.getNickname());
        return updatedPeer;
    }

    @Override
    public void delete(String nickname) {
        peerRepository.deleteById(nickname);
        logger.info("Peer with nickname{} was deleted", nickname);
    }

    @Override
    public List<String> columnNames() {
        return Arrays.asList("nickname", "birthday");
    }

    @Override
    public String tableName() {
        return "Peer";
    }
}
