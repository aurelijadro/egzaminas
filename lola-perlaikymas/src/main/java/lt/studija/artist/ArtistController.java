package lt.studija.artist;

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
import lt.studija.artist.Artist;
import lt.studija.artist.ArtistService;
import lt.studija.artist.NewArtist;

@RestController
@Api(value = "artist")
@RequestMapping(value = "/api/artists")
public class ArtistController {

	private ArtistService artistService;

	@Autowired
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get artists", notes = "Returns all artists")
	public List<Artist> getArtists() {
		return artistService.getArtists();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get artist by ID", notes = "Returns a single artist by ID")
	public Artist getArtistById(@ApiParam(value = "artist id", required = true) @Valid @PathVariable String id,
			HttpServletResponse response) {
		if (artistService.getArtistById(Long.parseLong(id)).isPresent()) {
			return artistService.getArtistById(Long.parseLong(id)).get();
		} else {
			response.setStatus(404);
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Artist", notes = "Creates new artist")
	public void createArtist(@ApiParam(value = "Artist Data", required = true) @Valid @RequestBody final NewArtist nl) {
		artistService.create(nl);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateArtistById(
			@ApiParam(value = "updated artist data", required = true) @Valid @PathVariable String id,
			@RequestBody final NewArtist nl, HttpServletResponse response) {
		if (artistService.getArtistById(Long.parseLong(id)).isPresent()) {
			artistService.edit(nl, Long.parseLong(id));
		} else {
			response.setStatus(404);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete artist", notes = "deletesartist by id")
	public void delete(@ApiParam(value = "Artist id", required = true) @PathVariable final String id) {
		artistService.deleteById(Long.parseLong(id));
	}

}
