package lt.studija.label;

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

@RestController
@Api(value = "label")
@RequestMapping(value = "/api/labels")
public class LabelController {

	private LabelService labelService;

	@Autowired
	public LabelController(LabelService labelService) {
		this.labelService = labelService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get labels", notes = "Returns all labels")
	public List<Label> getLabels() {
		return labelService.getLabels();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get label by ID", notes = "Returns a single label by ID")
	public Label getLabelById(@ApiParam(value = "label id", required = true) @Valid @PathVariable String id,
			HttpServletResponse response) {
		if (labelService.getLabelById(Long.parseLong(id)).isPresent()) {
			return labelService.getLabelById(Long.parseLong(id)).get();
		} else {
			response.setStatus(404);
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create Label", notes = "Creates new label")
	public void createLabel(@ApiParam(value = "Label Data", required = true) @Valid @RequestBody final NewLabel nl) {
		labelService.create(nl);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateLabelById(@ApiParam(value = "updated label data", required = true) @Valid @PathVariable String id,
			@RequestBody final NewLabel nl, HttpServletResponse response) {
		if (labelService.getLabelById(Long.parseLong(id)).isPresent()) {
			labelService.edit(nl, Long.parseLong(id));
		} else {
			response.setStatus(404);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete label", notes = "deleteslabel by id")
	public void delete(@ApiParam(value = "Label id", required = true) @PathVariable final String id) {
		labelService.deleteById(Long.parseLong(id));
	}
}
