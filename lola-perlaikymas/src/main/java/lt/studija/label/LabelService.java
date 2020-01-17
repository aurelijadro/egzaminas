package lt.studija.label;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LabelService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LabelService.class);

	private LabelRepository labelRepository;

	@Autowired
	public LabelService(LabelRepository labelRepository) {
		this.labelRepository = labelRepository;
	}

	@Transactional
	public List<Label> getLabels() {
		return labelRepository.findAll();
	}

	@Transactional
	public void deleteById(Long id) {
		labelRepository.deleteById(id);
	}

	@Transactional
	public Optional<Label> getLabelById(Long id) {
		return labelRepository.findById(id);
	}

	@Transactional
	public void create(NewLabel nl) {
		labelRepository.save(new Label(nl.getTitle(), nl.getLogo(), nl.getCategory(), nl.getSize()));
	}

	@Transactional
	public void edit(NewLabel nl, Long id) {
		if (labelRepository.findById(id).isPresent()) {
			Label label = labelRepository.findById(id).get();
			label.setTitle(nl.getTitle());
			label.setLogo(nl.getLogo());
			label.setCategory(nl.getCategory());
			label.setSize(nl.getSize());
			labelRepository.save(label);

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
