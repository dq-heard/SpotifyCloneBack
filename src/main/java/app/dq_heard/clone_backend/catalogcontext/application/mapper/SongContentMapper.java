package app.dq_heard.clone_backend.catalogcontext.application.mapper;

import app.dq_heard.clone_backend.catalogcontext.application.dto.SaveSongDTO;
import app.dq_heard.clone_backend.catalogcontext.application.dto.SongContentDTO;
import app.dq_heard.clone_backend.catalogcontext.domain.SongContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongContentMapper {

  @Mapping(source = "song.publicID", target = "publicID")
  SongContentDTO songContentToSongContentDTO(SongContent songContent);

  SongContent saveSongDTOToSong(SaveSongDTO songDTO);
}
