package com.ihsinformatics.spring.appgpa.service;

import java.util.List;

import com.ihsinformatics.spring.appgpa.model.Lookup;

public interface LookupService {

	/**
	 * This will return all Lookup from the database.
	 * 
	 * @return List<Lookup>
	 */
	List<Lookup> getAllLookup();

	/**
	 * This will give single Lookup info from the database.
	 * 
	 * @param id
	 * @return Lookup
	 */
	Lookup getLookupById(int id);

	/**
	 * This method will save an Lookup.
	 * 
	 * @param lookup
	 *            updated Lookup entity
	 * @return boolean whether the student saved or not.
	 */
	boolean save(Lookup lookup);

	/**
	 * This method will update a Lookup on the data.
	 * 
	 * @param lookup
	 *            updated entity
	 * @return boolean
	 */
	boolean update(Lookup lookup);

	/**
	 * This will return true if the Lookup successfully delete or not.
	 * 
	 * @param lookup
	 *            id provided.
	 * @return boolean
	 */
	boolean deleteLookupById(int id);
}
