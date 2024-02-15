package epicode.u5d7hw.repositories;

import epicode.u5d7hw.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostsDAO extends JpaRepository<Blogpost, Integer> {
}
