package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ControllerForSecurity {
	public String tokenreqUrl="https://oauth.brightcove.com/v4/access_token";
	public String CLIENT_ID = "b8ff7dea-e8ab-48fe-aa70-54221e46b705";
	public String CLIENT_SECRET ="grk1DbNkrMSe5ZtTF0rFAAl3wVh9MxgB8UJ4rR7ADBWmYU-twNTEMDc-zWHvIoqHHp4BSdYg-yfD82fyEtCWRg";
	public String ACCOUNT_ID = "6005208615001";
	 public String RESOURCE_URL_TPL =
	            "https://players.api.brightcove.com/v2/accounts/6005208615001/players";
	 String str="";
	 @GetMapping(value="GetAllPlayers",produces=MediaType.APPLICATION_JSON_VALUE)
	public String  GetAllPlayers() {
		 try {
	            OAuthClient client = new OAuthClient(new URLConnectionClient());

	            OAuthClientRequest request =
	                    OAuthClientRequest.tokenLocation(tokenreqUrl)
	                    .setGrantType(GrantType.CLIENT_CREDENTIALS)
	                    .setClientId(CLIENT_ID)
	                    .setClientSecret(CLIENT_SECRET)
	                    .buildQueryMessage();

	            String token =
	                    client.accessToken(request, OAuthJSONAccessTokenResponse.class)
	                    .getAccessToken();

	            String resourceUrl = RESOURCE_URL_TPL.replace(":account-id", ACCOUNT_ID);
	            HttpURLConnection resource_cxn =
	                    (HttpURLConnection)(new URL(resourceUrl).openConnection());
	            resource_cxn.addRequestProperty("Authorization", "Bearer " + token);

	            InputStream resource = resource_cxn.getInputStream();

	            
	            BufferedReader r = new BufferedReader(new InputStreamReader(resource, "UTF-8"));
	            String line = null;
	            
	            while ((line = r.readLine()) != null) {
	                System.out.println(line);
	                str=str+line;
	            }
	        } catch (Exception exn) {
	            exn.printStackTrace();
	        }
		 
		 return str;
	}
}
