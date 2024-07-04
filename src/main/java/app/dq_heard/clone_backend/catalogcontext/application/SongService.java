package app.dq_heard.clone_backend.catalogcontext.application;

import app.dq_heard.clone_backend.catalogcontext.application.dto.FavoriteSongDTO;
import app.dq_heard.clone_backend.catalogcontext.application.dto.ReadSongInfoDTO;
import app.dq_heard.clone_backend.catalogcontext.application.dto.SaveSongDTO;
import app.dq_heard.clone_backend.catalogcontext.application.dto.SongContentDTO;
import app.dq_heard.clone_backend.catalogcontext.application.mapper.SongContentMapper;
import app.dq_heard.clone_backend.catalogcontext.application.mapper.SongMapper;
import app.dq_heard.clone_backend.catalogcontext.domain.Favorite;
import app.dq_heard.clone_backend.catalogcontext.domain.FavoriteID;
import app.dq_heard.clone_backend.catalogcontext.domain.Song;
import app.dq_heard.clone_backend.catalogcontext.domain.SongContent;
import app.dq_heard.clone_backend.catalogcontext.repository.FavoriteRepository;
import app.dq_heard.clone_backend.catalogcontext.repository.SongContentRepository;
import app.dq_heard.clone_backend.catalogcontext.repository.SongRepository;
import app.dq_heard.clone_backend.infrastructure.service.dto.State;
import app.dq_heard.clone_backend.infrastructure.service.dto.StateBuilder;
import app.dq_heard.clone_backend.usercontext.ReadUserDTO;
import app.dq_heard.clone_backend.usercontext.application.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SongService {

  private final SongMapper songMapper;

  private final SongRepository songRepository;

  private final SongContentRepository songContentRepository;

  private final SongContentMapper songContentMapper;

  private final UserService userService;

  private final FavoriteRepository favoriteRepository;

  public SongService(SongMapper songMapper, SongRepository songRepository,
                     SongContentRepository songContentRepository, SongContentMapper songContentMapper, UserService userService, FavoriteRepository favoriteRepository) {
    this.songMapper = songMapper;
    this.songRepository = songRepository;
    this.songContentRepository = songContentRepository;
    this.songContentMapper = songContentMapper;
    this.userService = userService;
    this.favoriteRepository = favoriteRepository;
  }

  public ReadSongInfoDTO create(SaveSongDTO saveSongDTO) {
    Song song = songMapper.saveSongDTOToSong(saveSongDTO);
    Song songSaved = songRepository.save(song);

    SongContent songContent = songContentMapper.saveSongDTOToSong(saveSongDTO);
    songContent.setSong(songSaved);

    songContentRepository.save(songContent);
    return songMapper.songToReadSongInfoDTO(songSaved);
  }

  @Transactional(readOnly = true)
  public List<ReadSongInfoDTO> getAll() {

    List<ReadSongInfoDTO> allSongs = songRepository.findAll()
      .stream()
      .map(songMapper::songToReadSongInfoDTO)
      .toList();

    if(userService.isAuthenticated()) {
      return fetchFavoriteStatusForSongs(allSongs);
    }

    return allSongs;
  }

  public Optional<SongContentDTO> getOneByPublicID(UUID publicID) {
    Optional<SongContent> songByPublicID = songContentRepository.findOneBySongPublicID(publicID);
    return songByPublicID.map(songContentMapper::songContentToSongContentDTO);
  }

  public List<ReadSongInfoDTO> search(String searchTerm) {
    List<ReadSongInfoDTO> searchedSongs = songRepository.findByTitleOrArtistContaining(searchTerm)
      .stream()
      .map(songMapper::songToReadSongInfoDTO)
      .collect(Collectors.toList());

    if(userService.isAuthenticated()) {
      return fetchFavoriteStatusForSongs(searchedSongs);
    } else {
      return searchedSongs;
    }
  }

  public State<FavoriteSongDTO, String> addOrRemoveFromFavorites(FavoriteSongDTO favoriteSongDTO, String email) {
    StateBuilder<FavoriteSongDTO, String> builder = State.builder();
    Optional<Song> optSongLiked = songRepository.findOneByPublicID(favoriteSongDTO.publicID());
    if (optSongLiked.isEmpty()) {
      return builder.forError("This song's public ID doesn't exist.").build();
    }

    Song songToLike = optSongLiked.get();

    ReadUserDTO userWhoLikedSong = userService.getByEmail(email).orElseThrow();

    if (favoriteSongDTO.favorite()) {
      Favorite favorite = new Favorite();
      favorite.setSongPublicID(songToLike.getPublicID());
      favorite.setUserEmail(userWhoLikedSong.email());
      favoriteRepository.save(favorite);
    } else {
      FavoriteID favoriteID = new FavoriteID(songToLike.getPublicID(), userWhoLikedSong.email());
      favoriteRepository.deleteById(favoriteID);
      favoriteSongDTO = new FavoriteSongDTO(false, songToLike.getPublicID());
    }

    return builder.forSuccess(favoriteSongDTO).build();
  }

  public List<ReadSongInfoDTO> fetchFavoriteSongs(String email) {
    return songRepository.findAllFavoritesByUserEmail(email)
      .stream()
      .map(songMapper::songToReadSongInfoDTO)
      .toList();
  }

  private List<ReadSongInfoDTO> fetchFavoriteStatusForSongs(List<ReadSongInfoDTO> songs) {
    ReadUserDTO authenticatedUser = userService.getAuthenticatedUserFromSecurityContext();

    List<UUID> songPublicIDs = songs.stream().map(ReadSongInfoDTO::getPublicID).toList();

    List<UUID> userFavoriteSongs = favoriteRepository.findAllByUserEmailAndSongPublicIDIn(authenticatedUser.email(), songPublicIDs)
      .stream().map(Favorite::getSongPublicID).toList();

    return songs.stream().peek(song -> {
      if (userFavoriteSongs.contains(song.getPublicID())) {
        song.setFavorite(true);
      }
    }).toList();
  }
}
