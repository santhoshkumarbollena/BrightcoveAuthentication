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

import com.nimbusds.oauth2.sdk.AccessTokenResponse;



@RestController
public class ControllerForSecurity {
	
	 
	 String str="";
	 
	 @GetMapping(value="GetAllPlayers",produces=MediaType.APPLICATION_JSON_VALUE)
	 public String  GetAllPlayers() {
		 String RESOURCE_URL_TPL ="https://players.api.brightcove.com/v2/accounts/6005208615001/players";
		 try {
			 
			 AcccesTokenRequest ac=new AcccesTokenRequest();
			 ac.getAccessTOkenMethod();
	            String resourceUrl = RESOURCE_URL_TPL.replace(":account-id", ac.ACCOUNT_ID);
	            HttpURLConnection resource_cxn =
	                    (HttpURLConnection)(new URL(resourceUrl).openConnection());
	            resource_cxn.addRequestProperty("Authorization", "Bearer " + ac.token);

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
	 @GetMapping(value="GetCountryName",produces=MediaType.APPLICATION_JSON_VALUE)
	 public String getCountryNamesWherevideoBeingWatched() {
		 str="";
		 String RESOURCE_URL_TPL ="https://analytics.api.brightcove.com/v1/data?accounts=6005208615001&dimensions=country&fields=country_name";
		 try {
			 
			 AcccesTokenRequest ac=new AcccesTokenRequest();
			 ac.getAccessTOkenMethod();
	            String resourceUrl = RESOURCE_URL_TPL.replace(":account-id", ac.ACCOUNT_ID);
	            HttpURLConnection resource_cxn =
	                    (HttpURLConnection)(new URL(resourceUrl).openConnection());
	            resource_cxn.addRequestProperty("Authorization", "Bearer " + ac.token);

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
