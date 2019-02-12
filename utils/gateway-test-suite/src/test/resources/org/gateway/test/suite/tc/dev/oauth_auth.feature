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
   When I access the <apiUrl> url with queryParam ntid=myntid
   Then I get 200 status code and response payload: 
   """
	    {
			"dn": "uid=1234,ou=People",
			"name": "Som Nath Upadhyay",
			"email": "Somnath.Upadh@myorg.com",
			"uid": "1234",
			"myorgNtDomain": "CORP",
			"myorgNtLogin": "myntid",
			"myorgLoginName": "myntid",
			"costCenter": "123456"
		}
   """
   
    Examples:
      |node    | tokenUrl                                                  | apiUrl                                                    |
      | LB     | https://somehost.myorg.com/auth/oauth/v2/token            | https://somehost.myorg.com/dev/cpeldap/api/persons        |
      | dev02  | https://somehost02.myorg.com:8443/auth/oauth/v2/token     | https://somehost02.myorg.com:8443/dev/cpeldap/api/persons |
      | dev03  | https://somehost03.myorg.com:8443/auth/oauth/v2/token     | https://somehost03.myorg.com:8443/dev/cpeldap/api/persons |
      
         
  Scenario Outline: Authenticate Service with OAuth Password granty_type on <node>
   Given The oauth2 token <tokenUrl> and the payload:
   """
	    {
			"client_id": "l7xxc26a2c0585c94a32a54a0f6e22139765",
			"client_secret": "b8d35632422945c58866ee8aefc222cd",
			"grant_type": "password",
			"username": "somusr",
			"password": "emFiRE4xYVd3dGJIVnVSdz09PQ=="
		}
   """
   When I access the <apiUrl> url with queryParam ntid=myntid
   Then I get 200 status code and response payload: 
   """
	    {
			"dn": "uid=12345,ou=People",
			"name": "Som Nath Upadhyay",
			"email": "Somnath.Upadh@myorg.com",
			"uid": "1234",
			"myorgNtDomain": "CORP",
			"myorgNtLogin": "myntid",
			"myorgLoginName": "myntid",
			"costCenter": "123456"
		}
   """

    Examples:
      |node    | tokenUrl                                                  | apiUrl                                                    |
      | LB     | https://somehost.myorg.com/auth/oauth/v2/token            | https://somehost.myorg.com/dev/cpeldap/api/persons        |
      | dev02  | https://somehost02.myorg.com:8443/auth/oauth/v2/token     | https://somehost02.myorg.com:8443/dev/cpeldap/api/persons |
      | dev03  | https://somehost03.myorg.com:8443/auth/oauth/v2/token     | https://somehost03.myorg.com:8443/dev/cpeldap/api/persons |