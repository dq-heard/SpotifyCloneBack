package app.dq_heard.clone_backend.catalogcontext.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "favorite_song")
@IdClass(FavoriteID.class)
public class Favorite implements Serializable {

  @Id
  @Column(name = "song_public_id")
  private UUID songPublicID;

  @Id
  @Column(name = "user_email")
  private String userEmail;

  public UUID getSongPublicID() {
    return songPublicID;
  }

  public void setSongPublicID(UUID songPublicID) {
    this.songPublicID = songPublicID;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
}
