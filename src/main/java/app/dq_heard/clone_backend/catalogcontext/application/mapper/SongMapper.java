package app.dq_heard.clone_backend.catalogcontext.application.mapper;

import app.dq_heard.clone_backend.catalogcontext.application.dto.ReadSongInfoDTO;
import app.dq_heard.clone_backend.catalogcontext.application.dto.SaveSongDTO;
import app.dq_heard.clone_backend.catalogcontext.application.vo.SongArtistVO;
import app.dq_heard.clone_backend.catalogcontext.application.vo.SongTitleVO;
import app.dq_heard.clone_backend.catalogcontext.domain.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper {

  @Mapping(target = "ID", ignore = true)
  @Mapping(target = "publicID", ignore = true)
  Song saveSongDTOToSong(SaveSongDTO saveSongDTO);

  @Mapping(target = "favorite", ignore = true)
  ReadSongInfoDTO songToReadSongInfoDTO(Song song);

  default SongTitleVO stringToSongTitleVO(String title) {
    return new SongTitleVO(title);
  }

  default SongArtistVO stringToSongArtistVO(String artist) {
    return new SongArtistVO(artist);
  }

  default String songTitleVOToString(SongTitleVO title) {
    return title.value();
  }

  default String songArtistVOToString(SongArtistVO artist) {
    return artist.value();
  }
}
