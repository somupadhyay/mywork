@TstB2cEnv
Feature: Service Operations Validation.

  Scenario: Create a service in layer7 gateway using layer7 rest api.
   Given The layer7 rest api https://layer7-rest-api.cfcp.isus.emc.com/gateway/services and credentials emFZWEJwZFhObGNnPT09 and emFjR0Z6YzNkdmNtUT09
   When I somnath.upadhyay2@emc.com perform http POST operation with the json payload:
   """
   {
		"name": "B2C-Gateway-Automation-Test-API",
		"providerEmailIds": "somnath.upadhyay2@emc.com",
		"l7UrlPattern": "/temp/b2c/ldap*",
		"category": "Utilities",
		"backendUrl": "https://cpe-ldapapi.cf.isus.emc.com",
		"enabled": true,
		"apiType": "REST",
		"apiId": "",
		"platform": "ssgosgtst.isus.emc.com",
		"basicAuths": [{
			"userName": "tmp_l7apiuser",
			"password": "tmp_l7apiuser"
		}],
		"backendBasicAuthUserName": "cpe_apiuser",
		"backendBasicAuthPassword": "cpe_apiuser",
		"templateName": "",
		"authentication": "API Key Authentication Fragment,OAuth2 Authentication Fragment,Basic Auth,",
		"sourceWsdl": "",
		"id": "",
		"apiPlan": "medium",
		"externalFacing": "true"
	}
   """
   Then I should get the response status 200 and payload: 
   """
	    {
			"success": true,
			"summaryMessage": "Successfully created service",
			"data": {
				"id": "ab9ce13c9c5413f63f0bac8e3b0c670a",
				"name": "B2C-Gateway-Automation-Test-API",
				"l7Url": "https://ssgosgetst.emc.com/temp/b2c/ldap*",
				"l7UrlPattern": "/temp/b2c/ldap*",
				"category": "Utilities",
				"backendUrl": "https://cpe-ldapapi.cf.isus.emc.com",
				"enabled": true,
				"creationDate": null,
				"apiType": "REST",
				"platform": "ssgosgtst.isus.emc.com",
				"backendBasicAuthUserName": "cpe_apiuser",
				"backendBasicAuthPassword": "cpe_apiuser",
				"authentication": "API Key Authentication Fragment,OAuth2 Authentication Fragment,Basic Auth,",
				"basicAuths": [{
					"userName": "tmp_l7apiuser",
					"password": "tmp_l7apiuser"
				}],
				"apiPlan": "medium",
				"templateName": "",
				"soapVersion": "1.1",
				"providerEmailIds": "somnath.upadhyay2@emc.com",
				"sourceWsdl": "",
				"externalFacing": true,
				"oldName": null,
				"planitWR": null,
				"notes": null,
				"apiId": "c77b9a21-608c-4303-9e52-66f30385e138"
			}
		}
   """
   
    Scenario: Disable an existing service.
     Given The layer7 rest api https://layer7-rest-api.cfcp.isus.emc.com/gateway/services and credentials emFZWEJwZFhObGNnPT09 and emFjR0Z6YzNkdmNtUT09
     When I somnath.upadhyay2@emc.com request to disable the existing service B2C-Gateway-Automation-Test-API on ssgosgtst.isus.emc.com host
     Then I should get http status code as 200 and content:
     """
     	{
			"success": true,
			"summaryMessage": "Successfully updated service Status",
			"data": true
		}
     """
     
    Scenario: Test a disabled service it should fail. 
     Given The app https://ssgosgetst.emc.com/temp/b2c/ldap/api/persons auth tmp_l7apiuser and tmp_l7apiuser
     When I Search for ntid upadhs5
     Then I get the 500 response code 
    
    Scenario: Enable an existing disabled service.
     Given The layer7 rest api https://layer7-rest-api.cfcp.isus.emc.com/gateway/services and credentials emFZWEJwZFhObGNnPT09 and emFjR0Z6YzNkdmNtUT09
     When I somnath.upadhyay2@emc.com request to enable the existing service B2C-Gateway-Automation-Test-API on ssgosgtst.isus.emc.com host
     Then I should get http status code as 200 and content:
     """
     	{
			"success": true,
			"summaryMessage": "Successfully updated service Status",
			"data": true
		}
     """
    Scenario: Test the service after enabling it; the test should pass. 
     Given The app https://ssgosgetst.emc.com/temp/b2c/ldap/api/persons auth tmp_l7apiuser and tmp_l7apiuser
     When I Search for ntid upadhs5
     Then I get the 200 response code 
   
    Scenario: Delete an existing service.
     Given The layer7 rest api https://layer7-rest-api.cfcp.isus.emc.com/gateway/services and credentials emFZWEJwZFhObGNnPT09 and emFjR0Z6YzNkdmNtUT09
     When I somnath.upadhyay2@emc.com request to delete the existing service B2C-Gateway-Automation-Test-API on ssgosgtst.isus.emc.com host
     Then I should get http status code as 200 and content:
     """
     	{
			"success": true,
			"summaryMessage": "Successfully deleted service",
			"data": true
		}
     """