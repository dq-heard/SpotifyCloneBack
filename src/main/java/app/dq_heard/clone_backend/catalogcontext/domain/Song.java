package app.dq_heard.clone_backend.catalogcontext.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "song")
public class Song implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "songSequenceGenerator")
  @SequenceGenerator(name = "songSequenceGenerator", sequenceName = "song_generator", allocationSize = 1)
  @Column(name = "id")
  private Long ID;

  @UuidGenerator
  @Column(name = "public_id", nullable = false)
  private UUID publicID;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "artist", nullable = false)
  private String artist;

  @Lob
  @Column(name = "cover", nullable = false)
  private byte[] cover;

  @Column(name = "cover_content_type", nullable = false)
  private String coverContentType;

  public Long getID() {
    return ID;
  }

  public void setID(Long ID) {
    this.ID = ID;
  }

  public UUID getPublicID() {
    return publicID;
  }

  public void setPublicID(UUID publicID) {
    this.publicID = publicID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public byte[] getCover() {
    return cover;
  }

  public void setCover(byte[] cover) {
    this.cover = cover;
  }

  public String getCoverContentType() {
    return coverContentType;
  }

  public void setCoverContentType(String coverContentType) {
    this.coverContentType = coverContentType;
  }
}
