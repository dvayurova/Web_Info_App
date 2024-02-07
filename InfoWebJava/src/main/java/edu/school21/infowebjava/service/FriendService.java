package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Friend;
import edu.school21.infowebjava.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FriendService implements EntityService<Friend, Long>  {
    private final FriendRepository friendRepository;

    private static final Logger logger = LoggerFactory.getLogger(FriendService.class);

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
        Friend savedFriend = friendRepository.save(friend);
        logger.info("Friend with id {} was added", savedFriend.getId());
        return savedFriend;
    }

    @Override
    public Friend update(Friend friend){
        Friend updatedFriend = friendRepository.save(friend);
        logger.info("Friend with id {} was updated", updatedFriend.getId());
        return updatedFriend;
    }

    @Override
    public void delete(Long id){
        friendRepository.deleteById(id);
        logger.info("Friend with id {} was deleted", id);
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
