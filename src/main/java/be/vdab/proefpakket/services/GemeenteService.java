package be.vdab.proefpakket.services;

import java.util.List;

import be.vdab.proefpakket.entities.Gemeente;

public interface GemeenteService {
	public abstract List<Gemeente> findAll();
}
