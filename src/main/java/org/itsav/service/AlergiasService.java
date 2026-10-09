package org.itsav.service;

import org.itsav.domain.entities.Alergias;

import java.util.List;

public interface AlergiasService {
    public List<Alergias> findAll();
    public Alergias findById(Integer id);
}
