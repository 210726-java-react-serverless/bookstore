package com.revature.bookstore.web.intercom;

import com.revature.bookstore.web.dtos.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthServiceClient {

    private final RestTemplate restClient;
    @Value("${auth-endpoint}")
    private final String authServiceUrl = "http://authservice-env.eba-hmvam7bf.us-east-1.elasticbeanstalk.com/auth-service/token";

    @Autowired
    public AuthServiceClient(RestTemplate restClient) {
        this.restClient = restClient;
    }

    public String generateTokenFromPrincipal(Principal principal) {
        return restClient.postForObject(authServiceUrl, principal, String.class);
    }

    public boolean validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("bookstore-token", token);
        ResponseEntity<Boolean> response = restClient.exchange(authServiceUrl, HttpMethod.GET, new HttpEntity<>(headers), Boolean.class);
        if (response.hasBody() && response.getBody() != null) {
            return response.getBody();
        }
        return false;
    }

    public String getTokenAuthorities(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("bookstore-token", token);

        return restClient.exchange(authServiceUrl + "/authorities", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
    }

}
