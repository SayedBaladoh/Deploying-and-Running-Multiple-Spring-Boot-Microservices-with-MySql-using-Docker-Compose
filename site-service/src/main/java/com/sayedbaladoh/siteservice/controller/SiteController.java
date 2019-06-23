package com.sayedbaladoh.siteservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.sayedbaladoh.siteservice.client.UserClient;
import com.sayedbaladoh.siteservice.model.Site;
import com.sayedbaladoh.siteservice.service.SiteService;

/**
 * Sites Controller
 * 
 * @author SayedBaladoh
 *
 */
@RestController
@RequestMapping("/api/sites")
public class SiteController {

	@Autowired
	private SiteService siteService;

	@Autowired
	private UserClient userClient;

	@PostMapping
	public Site add(@RequestBody Site site) {
		return siteService.save(site);
	}

	@GetMapping
	public List<Site> findAll() {
		return siteService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<Site> site = siteService.get(id);
		if (site.isPresent())
			return new ResponseEntity<>(site.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>("Site not found with id: " + id, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/organization/{organizationId}")
	public List<Site> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		return siteService.getByOrganizationId(organizationId);
	}

	@GetMapping("/organization/{organizationId}/with-users")
	public List<Site> findByOrganizationWithUsers(@PathVariable("organizationId") Long organizationId) {
		List<Site> sites = siteService.getByOrganizationId(organizationId);

		sites.forEach(s -> {
			try {
				s.setUsers(userClient.getUser(s.getId()));
			} catch (RestClientException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		// sites.forEach(s -> s.setUsers(userClient.findBySiteId(s.getId())));
		return sites;
	}
}
