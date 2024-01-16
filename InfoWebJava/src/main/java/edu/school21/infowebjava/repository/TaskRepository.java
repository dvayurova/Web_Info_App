package edu.school21.infowebjava.repository;

import edu.school21.infowebjava.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
