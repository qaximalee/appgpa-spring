package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.LookupDao;
import com.ihsinformatics.spring.appgpa.dao.imp.LookupDaoImp;
import com.ihsinformatics.spring.appgpa.model.Lookup;
import com.ihsinformatics.spring.appgpa.service.LookupService;

@Service
@Transactional
public class LookupServiceImp implements LookupService {

	private LookupDao lookupDao;

	@Autowired
	public void setLookupDao(LookupDaoImp lookupDao) {
		this.lookupDao = lookupDao;
	}

	/**
	 * This will return all Lookup from the database.
	 * 
	 * @return List<Lookup>
	 */
	@Override
	public List<Lookup> getAllLookup() {
		// TODO Auto-generated method stub
		return this.lookupDao.getAllLookup();
	}

	/**
	 * This will give single Lookup info from the database.
	 * 
	 * @param id
	 * @return Lookup
	 */
	@Override
	public Lookup getLookupById(int id) {
		// TODO Auto-generated method stub
		return this.lookupDao.getLookupById(id);
	}

	/**
	 * This method will save an Lookup.
	 * 
	 * @param lookup
	 *            updated Lookup entity
	 * @return boolean whether the student saved or not.
	 */
	@Override
	public boolean save(Lookup lookup) {
		// TODO Auto-generated method stub
		return this.lookupDao.save(lookup);
	}

	/**
	 * This method will update a Lookup on the data.
	 * 
	 * @param lookup
	 *            updated entity
	 * @return boolean
	 */
	@Override
	public boolean update(Lookup lookup) {
		// TODO Auto-generated method stub
		return this.lookupDao.update(lookup);
	}

	/**
	 * This will return true if the Lookup successfully delete or not.
	 * 
	 * @param lookup
	 *            id provided.
	 * @return boolean
	 */
	@Override
	public boolean deleteLookupById(int id) {
		// TODO Auto-generated method stub
		return this.lookupDao.deleteLookupById(id);
	}
}
