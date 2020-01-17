package lt.studija.song;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.studija.song.Song;
import lt.studija.song.SongRepository;
import lt.studija.song.SongService;
import lt.studija.song.NewSong;

@Service
public class SongService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SongService.class);

	private SongRepository songRepository;

	@Autowired
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}

	@Transactional
	public List<Song> getSongs() {
		return songRepository.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		songRepository.deleteById(id);
	}

	@Transactional
	public Optional<Song> getSongById(Long id) {
		return songRepository.findById(id);
	}

	@Transactional
	public void create(NewSong ns) {
		songRepository.save(new Song(ns.getTitle(), ns.getAlbumName(), ns.getLengthInSeconds(), ns.getMp3File()));
	}

	@Transactional
	public void edit(NewSong ns, Long id) {
		if (songRepository.findById(id).isPresent()) {
			Song song = songRepository.findById(id).get();
			song.setTitle(ns.getTitle());
			song.setAlbumName(ns.getAlbumName());
			song.setLengthInSeconds(ns.getLengthInSeconds());
			song.setMp3File(ns.getMp3File());
			songRepository.save(song);

		}
	}

	@PostConstruct
	public void init() {
		LOGGER.info("Service bean is created. Classname: " + getClass().toString() + " . Scope: singelton");

	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("Service bean is destroyed. Classname: " + getClass().toString() + " . Scope: singelton");
	}
}
