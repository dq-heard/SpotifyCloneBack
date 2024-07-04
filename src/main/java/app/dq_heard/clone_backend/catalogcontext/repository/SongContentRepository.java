package app.dq_heard.clone_backend.catalogcontext.repository;

import app.dq_heard.clone_backend.catalogcontext.domain.SongContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SongContentRepository extends JpaRepository<SongContent, Long> {

  Optional<SongContent> findOneBySongPublicID(UUID publicID);

}
