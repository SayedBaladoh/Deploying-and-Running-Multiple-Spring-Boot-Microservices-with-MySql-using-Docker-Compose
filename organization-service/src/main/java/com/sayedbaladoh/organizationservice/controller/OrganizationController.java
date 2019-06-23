package com.sayedbaladoh.organizationservice.controller;

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

import com.sayedbaladoh.organizationservice.client.SiteClient;
import com.sayedbaladoh.organizationservice.client.UserClient;
import com.sayedbaladoh.organizationservice.model.Organization;
import com.sayedbaladoh.organizationservice.service.OrganizationService;

/**
 * Organizations Controller
 * 
 * @author SayedBaladoh
 *
 */
@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private SiteClient siteClient;
	@Autowired

	private UserClient userClient;

	@PostMapping
	public Organization add(@RequestBody Organization organization) {
		return organizationService.save(organization);
	}

	@GetMapping
	public List<Organization> findAll() {
		return organizationService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<Organization> organization = organizationService.get(id);
		if (organization.isPresent())
			return new ResponseEntity<>(organization.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>("Organization not found with id: " + id, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}/with-sites")
	public Organization findByIdWithSites(@PathVariable("id") Long id) {
		Optional<Organization> organizationOpt = organizationService.get(id);
		Organization organization = null;
		if (organizationOpt.isPresent()) {
			organization = organizationOpt.get();
			organization.setSites(siteClient.findByOrganization(organization.getId()));
		}
		return organization;
	}

	@GetMapping("/{id}/with-sites-with-users")
	public Organization findByIdWithSitesAndUsers(@PathVariable("id") Long id) {

		Optional<Organization> organizationOpt = organizationService.get(id);
		Organization organization = null;
		if (organizationOpt.isPresent()) {
			organization = organizationOpt.get();
			organization.setSites(siteClient.findByOrganizationWithUsers(organization.getId()));
		}
		return organization;
	}

	@GetMapping("/{id}/with-users")
	public Organization findByIdWithUsers(@PathVariable("id") Long id) {
		Optional<Organization> organizationOpt = organizationService.get(id);
		Organization organization = null;
		if (organizationOpt.isPresent()) {
			organization = organizationOpt.get();
			try {
				organization.setUsers(userClient.getUser(organization.getId()));
			} catch (RestClientException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return organization;
	}
}
