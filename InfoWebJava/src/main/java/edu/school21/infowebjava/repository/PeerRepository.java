package edu.school21.infowebjava.repository;


import edu.school21.infowebjava.models.Peer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeerRepository extends JpaRepository<Peer, String> {
}
