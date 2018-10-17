package be.vdab.proefpakket.services;

import java.util.List;

import be.vdab.proefpakket.entities.Brouwer;

public interface BrouwerService {
	public abstract List<Brouwer> findByBeginNaam(String beginNaam);
	public abstract void update(Brouwer brouwer);
	public abstract char[] findBeginLetters();
}
