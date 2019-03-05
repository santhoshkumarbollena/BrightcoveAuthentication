package com.example.demo;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class AcccesTokenRequest {
	public String tokenreqUrl="https://oauth.brightcove.com/v4/access_token";
	public String CLIENT_ID = "10cb4923-5936-41aa-a7b8-09a125ef0461";
	public String CLIENT_SECRET ="cyDxmF6lx9AHp6V4FajXKmE4TA7fT4jHkmVZt824hLcPTDspOP1seWvBnMutBLczfd_tYQ_B9QzTfCBrqWcCxA";
	public String ACCOUNT_ID = "6005208615001";
	public String token="";
	 public void getAccessTOkenMethod() throws Exception{
		  OAuthClient client = new OAuthClient(new URLConnectionClient());

          OAuthClientRequest request =
                  OAuthClientRequest.tokenLocation(tokenreqUrl)
                  .setGrantType(GrantType.CLIENT_CREDENTIALS)
                  .setClientId(CLIENT_ID)
                  .setClientSecret(CLIENT_SECRET)
                  .buildQueryMessage();

           token =
                  client.accessToken(request, OAuthJSONAccessTokenResponse.class)
                  .getAccessToken();
	 }

}
