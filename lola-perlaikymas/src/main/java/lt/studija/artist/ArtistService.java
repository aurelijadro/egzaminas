package lt.studija.artist;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.studija.artist.Artist;
import lt.studija.artist.ArtistRepository;
import lt.studija.artist.ArtistService;
import lt.studija.artist.NewArtist;

@Service
public class ArtistService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArtistService.class);

	private ArtistRepository artistRepository;

	@Autowired
	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}

	@Transactional
	public List<Artist> getArtists() {
		return artistRepository.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		artistRepository.deleteById(id);
	}

	@Transactional
	public Optional<Artist> getArtistById(Long id) {
		return artistRepository.findById(id);
	}

	@Transactional
	public void create(NewArtist na) {
		artistRepository.save(new Artist(na.getTitle(), na.getName(), na.getSurname(), na.getGenre(), na.getCountry(),
				na.getBirthday(), na.getPhoto()));
	}

	@Transactional
	public void edit(NewArtist na, Long id) {
		if (artistRepository.findById(id).isPresent()) {
			Artist artist = artistRepository.findById(id).get();
			artist.setTitle(na.getTitle());
			artist.setName(na.getName());
			artist.setSurname(na.getSurname());
			artist.setGenre(na.getGenre());
			artist.setCountry(na.getCountry());
			artist.setBirthday(na.getBirthday());
			artist.setPhoto(na.getPhoto());
			artistRepository.save(artist);

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
