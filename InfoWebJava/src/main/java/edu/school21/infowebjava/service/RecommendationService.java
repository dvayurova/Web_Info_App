package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Peer;
import edu.school21.infowebjava.models.Recommendation;
import edu.school21.infowebjava.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RecommendationService implements EntityService<Recommendation, Long>{
    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<? extends EntityInterface> getAll(){
        return recommendationRepository.findAll();
    }

    @Override
    public Recommendation findById(Long id){
        return recommendationRepository.findById(id).get();
    }

    @Override
    public Recommendation add(Recommendation recommendation){
        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation update(Recommendation recommendation){
        return recommendationRepository.save(recommendation);
    }

    @Override
    public void delete(Long id){
        recommendationRepository.deleteById(id);
    }
    @Override
    public List<String> columnNames(){
        return Arrays.asList("id", "peer", "recommendedPeer");
    }
    @Override
    public String tableName(){
        return "Recommendation";
    }
}
