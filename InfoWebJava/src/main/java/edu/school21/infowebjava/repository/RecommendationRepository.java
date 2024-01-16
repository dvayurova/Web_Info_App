package edu.school21.infowebjava.repository;

import edu.school21.infowebjava.models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}
