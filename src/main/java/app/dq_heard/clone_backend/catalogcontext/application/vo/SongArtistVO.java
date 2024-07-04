package app.dq_heard.clone_backend.catalogcontext.application.vo;

import jakarta.validation.constraints.NotBlank;

public record SongArtistVO(@NotBlank String value) {
}
