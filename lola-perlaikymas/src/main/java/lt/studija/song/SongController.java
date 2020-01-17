package lt.studija.song;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.studija.song.Song;
import lt.studija.song.SongService;
import lt.studija.song.NewSong;

@RestController
@Api(value = "song")
@RequestMapping(value = "/api/songs")
public class SongController {

	private SongService songService;

	@Autowired
	public SongController(SongService songService) {
		this.songService = songService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get songs", notes = "Returns all songs")
	public List<Song> getSongs() {
		return songService.getSongs();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get song by ID", notes = "Returns a single song by ID")
	public Song getSongById(@ApiParam(value = "song id", required = true) @Valid @PathVariable String id,
			HttpServletResponse response) {
		if (songService.getSongById(Long.parseLong(id)).isPresent()) {
			return songService.getSongById(Long.parseLong(id)).get();
		} else {
			response.setStatus(404);
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Song", notes = "Creates new song")
	public void createSong(@ApiParam(value = "Song Data", required = true) @Valid @RequestBody final NewSong ns) {
		songService.create(ns);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateSongById(@ApiParam(value = "updated song data", required = true) @Valid @PathVariable String id,
			@RequestBody final NewSong ns, HttpServletResponse response) {
		if (songService.getSongById(Long.parseLong(id)).isPresent()) {
			songService.edit(ns, Long.parseLong(id));
		} else {
			response.setStatus(404);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete song", notes = "deletes song by id")
	public void delete(@ApiParam(value = "Song id", required = true) @PathVariable final String id) {
		songService.deleteById(Long.parseLong(id));
	}

}
