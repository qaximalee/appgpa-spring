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

	@Override
	public List<Lookup> getAllLookup() {
		// TODO Auto-generated method stub
		return this.lookupDao.getAllLookup();
	}

	@Override
	public Lookup getLookupById(int id) {
		// TODO Auto-generated method stub
		return this.lookupDao.getLookupById(id);
	}

	@Override
	public boolean save(Lookup lookup) {
		// TODO Auto-generated method stub
		return this.lookupDao.save(lookup);
	}

	@Override
	public boolean update(Lookup lookup) {
		// TODO Auto-generated method stub
		return this.lookupDao.update(lookup);
	}

	@Override
	public boolean deleteLookupById(int id) {
		// TODO Auto-generated method stub
		return this.lookupDao.deleteLookupById(id);
	}
}
