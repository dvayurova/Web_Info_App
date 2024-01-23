package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Friend;
import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FriendService implements EntityService<Friend, Long>  {
    private final FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return friendRepository.findAll();
    }

    @Override
    public Friend findById(Long id){
        return friendRepository.findById(id).get();
    }


    @Override
    public Friend add(Friend friend){
        return friendRepository.save(friend);
    }

    @Override
    public Friend update(Friend friend){
        return friendRepository.save(friend);
    }

    @Override
    public void delete(Long id){
        friendRepository.deleteById(id);
    }

    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "peer1", "peer2");
    }

    @Override
    public String tableName(){
        return "Friend";
    }
}
