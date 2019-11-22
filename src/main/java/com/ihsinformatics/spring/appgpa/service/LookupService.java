package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Lookup;

public interface LookupService {

	public List<Lookup> getAll();

	public Lookup getSingle(int id);

	public boolean save(Lookup lookup);

	public boolean update(Lookup lookup);

	public boolean delete(int id);
}
