package edu.school21.infowebjava.service;

import edu.school21.infowebjava.models.EntityInterface;
import edu.school21.infowebjava.models.Recommendation;
import edu.school21.infowebjava.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RecommendationService implements EntityService<Recommendation, Long>{

    private static final Logger logger = LoggerFactory.getLogger(RecommendationService.class);
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
        Recommendation savedRecommendation =  recommendationRepository.save(recommendation);
        logger.info("new Recommendation with id {} was added", savedRecommendation.getId());
        return savedRecommendation;
    }

    @Override
    public Recommendation update(Recommendation recommendation){
        Recommendation updatedRecommendation =  recommendationRepository.save(recommendation);
        logger.info("Recommendation with id {} was updated", updatedRecommendation.getId());
        return updatedRecommendation;
    }

    @Override
    public void delete(Long id){
        recommendationRepository.deleteById(id);
        logger.info("Recommendation with id{} was deleted", id);
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
