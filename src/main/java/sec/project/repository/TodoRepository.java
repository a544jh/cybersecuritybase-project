package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.TodoItem;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {

}
