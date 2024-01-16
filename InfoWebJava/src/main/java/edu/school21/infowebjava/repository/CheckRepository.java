package edu.school21.infowebjava.repository;

import edu.school21.infowebjava.models.Check;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<Check, Long> {
}
