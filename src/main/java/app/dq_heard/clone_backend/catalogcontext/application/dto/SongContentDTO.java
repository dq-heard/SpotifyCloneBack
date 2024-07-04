package app.dq_heard.clone_backend.catalogcontext.application.dto;

import jakarta.persistence.Lob;

import java.util.UUID;

public record SongContentDTO(UUID publicID, @Lob byte[] file, String fileContentType) {
}
