package app.dq_heard.clone_backend.catalogcontext.application.dto;

import app.dq_heard.clone_backend.catalogcontext.application.vo.SongArtistVO;
import app.dq_heard.clone_backend.catalogcontext.application.vo.SongTitleVO;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ReadSongInfoDTO {

  private SongTitleVO title;

  private SongArtistVO artist;

  @NotNull
  private byte[] cover;

  @NotNull
  private String coverContentType;

  @NotNull
  private boolean isFavorite;

  @NotNull
  private UUID publicID;

  public SongTitleVO getTitle() {
    return title;
  }

  public void setTitle(SongTitleVO title) {
    this.title = title;
  }

  public SongArtistVO getArtist() {
    return artist;
  }

  public void setArtist(SongArtistVO artist) {
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

  public boolean isFavorite() {
    return isFavorite;
  }

  public void setFavorite(boolean favorite) {
    isFavorite = favorite;
  }

  public UUID getPublicID() {
    return publicID;
  }

  public void setPublicID(UUID publicID) {
    this.publicID = publicID;
  }
}
