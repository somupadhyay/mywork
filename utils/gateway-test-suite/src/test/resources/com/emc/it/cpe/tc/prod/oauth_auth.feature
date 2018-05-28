@PrdEnv
Feature: OAuth Authorization Validation

  Scenario Outline: Authenticate Service with OAuth Client Credentials granty_type on <node>
   Given The oauth2 token <tokenUrl> and the payload:
   """
	    {
			"client_id":"l7xxa7542d8dda7c4c4ab08771f2c5bf01c8",
			"client_secret":"8d5738344a48447db7cf868a3aa011b9",
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
      | LB     | https://ssgosghop.isus.emc.com/auth/oauth/v2/token            | https://ssgosghop.isus.emc.com/prd/cpeldap/api/persons        |
      | prd03  | https://ssgosgprd03.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd03.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | prd04  | https://ssgosgprd04.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd04.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | prd09  | https://ssgosgprd09.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd09.isus.emc.com:8443/prd/cpeldap/api/persons |
      | prd10  | https://ssgosgprd10.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd10.isus.emc.com:8443/prd/cpeldap/api/persons |
      | prd11  | https://ssgosgprd11.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd11.isus.emc.com:8443/prd/cpeldap/api/persons |
      | prd13  | https://ssgosgprd13.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd13.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | durLB  | https://ssgosgdur.isus.emc.com/auth/oauth/v2/token            | https://ssgosgdur.isus.emc.com/prd/cpeldap/api/persons        |
      | prd07  | https://ssgosgprd07.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd07.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | prd08  | https://ssgosgprd08.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd08.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | prd12  | https://ssgosgprd12.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd12.isus.emc.com:8443/prd/cpeldap/api/persons |      
         
  Scenario Outline: Authenticate Service with OAuth Password granty_type on <node>
   Given The oauth2 token <tokenUrl> and the payload:
   """
	    {
			"client_id": "l7xxa7542d8dda7c4c4ab08771f2c5bf01c8",
			"client_secret": "8d5738344a48447db7cf868a3aa011b9",
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
      | LB     | https://ssgosghop.isus.emc.com/auth/oauth/v2/token            | https://ssgosghop.isus.emc.com/prd/cpeldap/api/persons        |
      | prd03  | https://ssgosgprd03.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd03.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | prd04  | https://ssgosgprd04.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd04.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | prd09  | https://ssgosgprd09.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd09.isus.emc.com:8443/prd/cpeldap/api/persons |
      | prd10  | https://ssgosgprd10.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd10.isus.emc.com:8443/prd/cpeldap/api/persons |
      | prd11  | https://ssgosgprd11.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd11.isus.emc.com:8443/prd/cpeldap/api/persons |
      | prd13  | https://ssgosgprd13.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd13.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | durLB  | https://ssgosgdur.isus.emc.com/auth/oauth/v2/token            | https://ssgosgdur.isus.emc.com/prd/cpeldap/api/persons        |
      | prd07  | https://ssgosgprd07.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd07.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | prd08  | https://ssgosgprd08.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd08.isus.emc.com:8443/prd/cpeldap/api/persons | 
      | prd12  | https://ssgosgprd12.isus.emc.com:8443/auth/oauth/v2/token     | https://ssgosgprd12.isus.emc.com:8443/prd/cpeldap/api/persons |       
   