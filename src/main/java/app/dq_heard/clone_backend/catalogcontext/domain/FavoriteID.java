package app.dq_heard.clone_backend.catalogcontext.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FavoriteID implements Serializable {

  UUID songPublicID;

  String userEmail;

  public FavoriteID() {
    // Default constructor required by Hibernate
  }

  public FavoriteID(UUID songPublicID, String userEmail) {
    this.songPublicID = songPublicID;
    this.userEmail = userEmail;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FavoriteID that = (FavoriteID) o;
    return Objects.equals(songPublicID, that.songPublicID) && Objects.equals(userEmail, that.userEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(songPublicID, userEmail);
  }
}
