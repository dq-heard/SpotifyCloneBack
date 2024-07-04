package app.dq_heard.clone_backend.catalogcontext.repository;

import app.dq_heard.clone_backend.catalogcontext.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, Long> {

  @Query("SELECT s FROM Song s WHERE lower(s.title) LIKE lower(concat('%',:searchTerm,'%'))" +
      "OR lower(s.artist) LIKE lower(concat('%',:searchTerm,'%'))")
  List<Song> findByTitleOrArtistContaining(String searchTerm);

  Optional<Song> findOneByPublicID(UUID publicID);

  @Query("SELECT s FROM Song s JOIN Favorite f ON s.publicID = f.songPublicID WHERE f.userEmail = :email")
  List<Song> findAllFavoritesByUserEmail(String email);

}
