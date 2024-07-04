package app.dq_heard.clone_backend.catalogcontext.application.dto;

import app.dq_heard.clone_backend.catalogcontext.application.vo.SongArtistVO;
import app.dq_heard.clone_backend.catalogcontext.application.vo.SongTitleVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SaveSongDTO(@Valid SongTitleVO title,
                          @Valid SongArtistVO artist,
                          @NotNull byte[] cover,
                          @NotNull String coverContentType,
                          @NotNull byte[] file,
                          @NotNull String fileContentType) {
}
