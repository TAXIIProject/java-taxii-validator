/**
 * Copyright (c) 2015, The MITRE Corporation. All rights reserved.
 * See LICENSE for complete terms.
 */
package org.mitre.taxii.validator;

import static spark.Spark.post;

/**
 * A Validator Resource 
 * 
 * @author nemonik (Michael Joseph Walsh <github.com@nemonik.com>)
 *
 */
public class ValidatorResource {
	private static final String API_CONTEXT = "/api/v1";

	private  final ValidatorService validatorService;

	/**
	 * Creates a validator resource of the validator service wired
	 * to specific routes.
	 * 
	 * @param validatorService The ValidatorService to wire.
	 */
	public ValidatorResource(ValidatorService validatorService) {
		this.validatorService = validatorService;
		setupEndpoints();
	}

	/**
	 * Wires the the service to specified routes
	 */
	private void setupEndpoints() {
		post(API_CONTEXT + "/validate/url", "application/json", (request, response)
				-> validatorService.validateURL(request.body()), new JsonTransformer());
		
		post(API_CONTEXT + "/validate/xml", "application/json", (request, response)
				-> validatorService.validateXML(request.body()), new JsonTransformer());
	}
}