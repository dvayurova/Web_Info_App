package edu.school21.infowebjava.repository;

import edu.school21.infowebjava.models.TimeTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTrackerRepository extends JpaRepository<TimeTracker, Long> {
}
