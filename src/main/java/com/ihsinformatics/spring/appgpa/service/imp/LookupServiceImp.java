package com.ihsinformatics.spring.appgpa.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ihsinformatics.spring.appgpa.dao.LookupDAO;
import com.ihsinformatics.spring.appgpa.dao.imp.LookupDAOImp;
import com.ihsinformatics.spring.appgpa.model.Lookup;
import com.ihsinformatics.spring.appgpa.service.LookupService;

@Service
@Transactional
public class LookupServiceImp implements LookupService {

	private LookupDAO lookupDAO;

	@Autowired
	public void setLookupDAO(LookupDAOImp lookupDAO) {
		this.lookupDAO = lookupDAO;
	}

	@Override
	public List<Lookup> getAll() {
		// TODO Auto-generated method stub
		return this.lookupDAO.getAll();
	}

	@Override
	public Lookup getSingle(int id) {
		// TODO Auto-generated method stub
		return this.lookupDAO.getSingle(id);
	}

	@Override
	public boolean save(Lookup lookup) {
		// TODO Auto-generated method stub
		return this.lookupDAO.save(lookup);
	}

	@Override
	public boolean update(Lookup lookup) {
		// TODO Auto-generated method stub
		return this.lookupDAO.update(lookup);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return this.lookupDAO.delete(id);
	}
}
