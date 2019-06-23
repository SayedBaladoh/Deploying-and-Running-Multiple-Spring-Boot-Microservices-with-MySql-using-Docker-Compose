package com.sayedbaladoh.siteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayedbaladoh.siteservice.model.Site;
import com.sayedbaladoh.siteservice.repository.SiteRepository;

/**
 * Site Service
 * 
 * @author SayedBaladoh
 *
 */
@Service
public class SiteService {

	@Autowired
	private SiteRepository siteRepository;

	public Site save(Site site) {
		return siteRepository.save(site);
	}

	public List<Site> getAll() {
		List<Site> sites = siteRepository.findAll();
		return sites;
	}

	public Optional<Site> get(Long id) {
		return siteRepository.findById(id);
	}

	public void delete(Long id) {
		siteRepository.deleteById(id);
	}

	public List<Site> getByOrganizationId(Long organizationId) {
		return siteRepository.findByOrganizationId(organizationId);
	}

}
