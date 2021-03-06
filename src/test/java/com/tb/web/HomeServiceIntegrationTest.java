package com.tb.web;

import com.tb.SpringbootBackendStarterApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by Tivadar Bocz on 2017.01.27..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootBackendStarterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeServiceIntegrationTest {
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    private TestRestTemplate template = new TestRestTemplate();

    @Before
    public void setupJSONAcceptType() {
        headers.add("Authorization", createBasicAuthenticationHeaderValue("user", "user"));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getWelcomeMessgae() throws Exception {

        String expected = "Hello";

        ResponseEntity<String> response = template.exchange(
                createUrlWithRandomPort("/home/public"), HttpMethod.GET, new HttpEntity<>(null, null), String.class);

        //JSONAssert.assertEquals(expected, response.getBody(), false);
        Assert.assertEquals(expected, response.getBody().toString());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getSecuredWelcomeMessage() throws Exception {
        String expected = "Hello secured";

        /**
         * It has to result with 401 without authorization header
         */
        ResponseEntity<String> responseWithoutHeader = template.exchange(
                createUrlWithRandomPort("/home/secured"), HttpMethod.GET, new HttpEntity<>(null, null), String.class);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseWithoutHeader.getStatusCode());
        /**
         * It has to result with 200 with authorization header
         */
        ResponseEntity<String> responseWithHeader = template.exchange(
                createUrlWithRandomPort("/home/secured"), HttpMethod.GET, new HttpEntity<>(null, headers), String.class);
        Assert.assertEquals(HttpStatus.OK, responseWithHeader.getStatusCode());

        Assert.assertEquals(expected, responseWithHeader.getBody().toString());
    }


    private String createUrlWithRandomPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private String createBasicAuthenticationHeaderValue(String userName, String password) {
        //Authorization:Basic Base64Encoding(userName:password)
        String auth = userName + ":" + password;
        byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
        String basicHeaderValue = "Basic " + new String(encodedAuth);

        return basicHeaderValue;
    }
}
