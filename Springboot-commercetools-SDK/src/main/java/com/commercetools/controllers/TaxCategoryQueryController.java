package com.commercetools.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.commercetools.service.TaxCategoryQuery;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping(value = "/commercetools")
public class TaxCategoryQueryController {
	
	@Autowired
	private TaxCategoryQuery taxCategoryQuery;
	
	@RequestMapping(value = "/getTaxCategory", method = RequestMethod.GET)
	public void getTaxCategory() {
		taxCategoryQuery.queryByNameExample();		
	}

}
