package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.Friend;
import edu.school21.infowebjava.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    private final FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> getAllFriends(){
        return friendRepository.findAll();
    }

    public Friend addFriend(Friend friend){
        return friendRepository.save(friend);
    }

    public Friend updateFriend(Friend friend){
        return friendRepository.save(friend);
    }

    public void deleteFriend(Long id){
        friendRepository.deleteById(id);
    }
}
