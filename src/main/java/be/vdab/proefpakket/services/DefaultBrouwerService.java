package be.vdab.proefpakket.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.repositories.BrouwerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultBrouwerService implements BrouwerService {

	private final BrouwerRepository repository;
	
	
	DefaultBrouwerService(BrouwerRepository repository) {
		this.repository = repository;
	}


	@Override
	public List<Brouwer> findByBeginNaam(String beginNaam) {
		return repository.findByNaamStartingWithOrderByNaam(beginNaam);
	}

	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	@Override
	public void update(Brouwer brouwer) {
		repository.save(brouwer);
	}


	@Override
	public char[] findBeginLetters() {
		List<Brouwer> brouwers = this.repository.findAll(Sort.by("naam"));
		return brouwers.stream()
			.map(br -> br.getNaam().substring(0, 1).toUpperCase())
			.distinct()
			.collect(Collectors.joining())
			.toCharArray();
	}

}
