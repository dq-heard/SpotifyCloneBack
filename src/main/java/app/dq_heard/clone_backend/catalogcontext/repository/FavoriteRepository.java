package app.dq_heard.clone_backend.catalogcontext.repository;

import app.dq_heard.clone_backend.catalogcontext.domain.Favorite;
import app.dq_heard.clone_backend.catalogcontext.domain.FavoriteID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteID> {
  List<Favorite> findAllByUserEmailAndSongPublicIDIn(String email, List<UUID> songPublicIDs);
}
