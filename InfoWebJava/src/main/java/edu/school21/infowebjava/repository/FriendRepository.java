package edu.school21.infowebjava.repository;

import edu.school21.infowebjava.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
