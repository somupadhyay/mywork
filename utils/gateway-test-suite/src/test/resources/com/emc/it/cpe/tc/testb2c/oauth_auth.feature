@TstB2cEnv
Feature: OAuth Authorization Validation

  Scenario Outline: Authenticate Service with OAuth Client Credentials granty_type on <node>
   Given The oauth2 token <tokenUrl> and the payload:
   """
	    {
			"client_id":"l7xx1965261573044475bdb4994736dee46a",
			"client_secret":"765a8aa1c1494daca4c4efec0b3d6940",
			"grant_type":"client_credentials"
		}
   """
   When I access the <apiUrl> url with queryParam ntid=upadhs5
   Then I get 200 status code and response payload: 
   """
	    {
			"dn": "uid=1022313,ou=People",
			"name": "Som Nath Upadhyay",
			"email": "Somnath.Upadhyay2@emc.com",
			"uid": "1022313",
			"emcNtDomain": "CORP",
			"emcNtLogin": "upadhs5",
			"emcLoginName": "upadhs5",
			"costCenter": "SIN1218011"
		}
   """
   
    Examples:
      |node    | tokenUrl                                                      | apiUrl                                                     |
      | LB     | https://ssgosgetst.emc.com/external/auth/oauth/v2/token       | https://ssgosgetst.emc.com/tst/b2c/ldap/api/persons        |
      
         
  Scenario Outline: Authenticate Service with OAuth Password granty_type on <node>
   Given The oauth2 token <tokenUrl> and the payload:
   """
	    {
			"client_id": "l7xx1965261573044475bdb4994736dee46a",
			"client_secret": "765a8aa1c1494daca4c4efec0b3d6940",
			"grant_type": "password",
			"username": "cmtuser",
			"password": "emFiRE4xYVd3dGJIVnVSdz09PQ=="
		}
   """
   When I access the <apiUrl> url with queryParam ntid=upadhs5
   Then I get 200 status code and response payload: 
   """
	    {
			"dn": "uid=1022313,ou=People",
			"name": "Som Nath Upadhyay",
			"email": "Somnath.Upadhyay2@emc.com",
			"uid": "1022313",
			"emcNtDomain": "CORP",
			"emcNtLogin": "upadhs5",
			"emcLoginName": "upadhs5",
			"costCenter": "SIN1218011"
		}
   """

    Examples:
      |node    | tokenUrl                                                      | apiUrl                                                     |
      | LB     | https://ssgosgetst.emc.com/external/auth/oauth/v2/token       | https://ssgosgetst.emc.com/tst/b2c/ldap/api/persons        |
  