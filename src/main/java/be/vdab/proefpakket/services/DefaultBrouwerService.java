package be.vdab.proefpakket.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.repositories.BrouwerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBrouwerService implements BrouwerService {

	private final BrouwerRepository repository;
	
	
	DefaultBrouwerService(BrouwerRepository repository) {
		this.repository = repository;
	}


	@Override
	public List<Brouwer> findByBeginNaam(String beginNaam) {
		return repository.findByNaamStartingWithOrderByNaam(beginNaam);
	}


	
}
