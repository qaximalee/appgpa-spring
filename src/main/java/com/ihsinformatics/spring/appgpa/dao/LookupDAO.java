package com.ihsinformatics.spring.appgpa.dao;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Lookup;

public interface LookupDAO {

	List<Lookup> getAllLookup();

	Lookup getLookupById(int id);

	boolean save(Lookup lookup);

	boolean update(Lookup lookup);

	boolean deleteLookupById(int id);
}
