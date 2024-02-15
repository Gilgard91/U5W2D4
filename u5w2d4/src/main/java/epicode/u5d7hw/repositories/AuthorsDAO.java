package epicode.u5d7hw.repositories;

import epicode.u5d7hw.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsDAO extends JpaRepository<Author, Integer> {
}
