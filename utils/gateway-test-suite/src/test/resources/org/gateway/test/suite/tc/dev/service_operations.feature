@DevEnv
Feature: Service Operations Validation.

  Scenario: Create a service in lyrs gateway using lyrs rest api.
   Given The lyrs rest api https://somehost.com/gateway/services and credentials emFZWEJwZFhObGNnPT09 and emFjR0Z6YzNkdmNtUT09
   When I somnath.upad@myorg.com perform http POST operation with the json payload:
   """
   {
		"name": "Gateway-Automation-Test-API",
		"providerEmailIds": "somnath.upad@myorg.com",
		"l7UrlPattern": "/temp/test/ldap*",
		"category": "Utilities",
		"backendUrl": "https://ldapapi.cf.myorg.com",
		"enabled": true,
		"apiType": "REST",
		"apiId": "",
		"platform": "somehost.myorg.com",
		"basicAuths": [{
			"userName": "tmp_l7apiuser",
			"password": "tmp_l7apiuser"
		}],
		"backendBasicAuthUserName": "hhh_apiuser",
		"backendBasicAuthPassword": "hhh_apiuser",
		"templateName": "",
		"authentication": "API Key Authentication Fragment,OAuth2 Authentication Fragment,Basic Auth,",
		"sourceWsdl": "",
		"id": "",
		"apiPlan": "medium",
		"externalFacing": "false"
	}
   """
   Then I should get the response status 200 and payload: 
   """
	    {
			"success": true,
			"summaryMessage": "Successfully created service",
			"data": {
				"id": "ab9ce13c9c5413f63f0bac8e3b0c670a",
				"name": "Gateway-Automation-Test-API",
				"l7Url": "https://somehost.myorg.com/temp/test/ldap*",
				"l7UrlPattern": "/temp/test/ldap*",
				"category": "Utilities",
				"backendUrl": "https://ldapapi.cf.myorg.com",
				"enabled": true,
				"creationDate": null,
				"apiType": "REST",
				"platform": "somehost.myorg.com",
				"backendBasicAuthUserName": "hhh_apiuser",
				"backendBasicAuthPassword": "hhh_apiuser",
				"authentication": "API Key Authentication Fragment,OAuth2 Authentication Fragment,Basic Auth,",
				"basicAuths": [{
					"userName": "tmp_l7apiuser",
					"password": "tmp_l7apiuser"
				}],
				"apiPlan": "medium",
				"templateName": "",
				"soapVersion": "1.1",
				"providerEmailIds": "somnath.upad@myorg.com",
				"sourceWsdl": "",
				"externalFacing": false,
				"oldName": null,
				"planitWR": null,
				"notes": null,
				"apiId": "c77b9a21-608c-4303-9e52-66f30385e138"
			}
		}
   """
   
    Scenario: Disable an existing service.
     Given The lyrs rest api https://somehost.com/gateway/services and credentials emFZWEJwZFhObGNnPT09 and emFjR0Z6YzNkdmNtUT09
     When I somnath.upad@myorg.com request to disable the existing service Gateway-Automation-Test-API on somehost.myorg.com host
     Then I should get http status code as 200 and content:
     """
     	{
			"success": true,
			"summaryMessage": "Successfully updated service Status",
			"data": true
		}
     """
     
    Scenario: Test a disabled service it should fail. 
     Given The app https://somehost.myorg.com/temp/test/ldap/api/persons auth tmp_l7apiuser and tmp_l7apiuser
     When I Search for ntid somusr
     Then I get the 500 response code 
    
    Scenario: Enable an existing disabled service.
     Given The lyrs rest api https://somehost.com/gateway/services and credentials emFZWEJwZFhObGNnPT09 and emFjR0Z6YzNkdmNtUT09
     When I somnath.upad@myorg.com request to enable the existing service Gateway-Automation-Test-API on somehost.myorg.com host
     Then I should get http status code as 200 and content:
     """
     	{
			"success": true,
			"summaryMessage": "Successfully updated service Status",
			"data": true
		}
     """
    Scenario: Test the service after enabling it; the test should pass. 
     Given The app https://somehost.myorg.com/temp/test/ldap/api/persons auth tmp_l7apiuser and tmp_l7apiuser
     When I Search for ntid somusr
     Then I get the 200 response code 
   
    Scenario: Delete an existing service.
     Given The lyrs rest api https://somehost.com/gateway/services and credentials emFZWEJwZFhObGNnPT09 and emFjR0Z6YzNkdmNtUT09
     When I somnath.upad@myorg.com request to delete the existing service Gateway-Automation-Test-API on somehost.myorg.com host
     Then I should get http status code as 200 and content:
     """
     	{
			"success": true,
			"summaryMessage": "Successfully deleted service",
			"data": true
		}
     """