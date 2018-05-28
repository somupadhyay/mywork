@DevEnv
Feature: OAuth Authorization Validation

  Scenario Outline: Authenticate Service with OAuth Client Credentials granty_type on <node>
   Given The oauth2 token <tokenUrl> and the payload:
   """
	    {
			"client_id":"l7xxc26a2c0585c94a32a54a0f6e22139765",
			"client_secret":"b8d35632422945c58866ee8aefc222cd",
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
      |node    | tokenUrl                                                      | apiUrl                                                        |
      | LB     | https://ssgosgdev.isus.emc.com/auth/oauth/v2/token            | https://ssgosgdev.isus.emc.com/dev/cpeldap/api/persons        |
      | dev02  | https://ssgosgdev02.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgdev02.isus.emc.com:8443/dev/cpeldap/api/persons |
      | dev03  | https://ssgosgdev03.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgdev03.isus.emc.com:8443/dev/cpeldap/api/persons |
      
         
  Scenario Outline: Authenticate Service with OAuth Password granty_type on <node>
   Given The oauth2 token <tokenUrl> and the payload:
   """
	    {
			"client_id": "l7xxc26a2c0585c94a32a54a0f6e22139765",
			"client_secret": "b8d35632422945c58866ee8aefc222cd",
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
      |node    | tokenUrl                                                      | apiUrl                                                        |
      | LB     | https://ssgosgdev.isus.emc.com/auth/oauth/v2/token            | https://ssgosgdev.isus.emc.com/dev/cpeldap/api/persons        |
      | dev02  | https://ssgosgdev02.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgdev02.isus.emc.com:8443/dev/cpeldap/api/persons |
      | dev03  | https://ssgosgdev03.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgdev03.isus.emc.com:8443/dev/cpeldap/api/persons |