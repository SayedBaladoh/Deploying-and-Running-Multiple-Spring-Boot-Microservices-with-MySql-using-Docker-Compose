package com.sayedbaladoh.organizationservice.client;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.sayedbaladoh.organizationservice.model.Site;

@Component
public class SiteClient {

	public List<Site> findByOrganization(@PathVariable("organizationId") Long organizationId){
		String baseUrl = "http://site:8082/api/sites/organization/" + organizationId;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), List.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
//		System.out.println(response.getBody());
		return response.getBody();
	}

	public List<Site> findByOrganizationWithUsers(@PathVariable("organizationId") Long organizationId){

		String baseUrl = "http://site:8082/api/sites/organization/" + organizationId+"/with-users";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), List.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
//		System.out.println(response.getBody());
		return response.getBody();
	
	}


	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
