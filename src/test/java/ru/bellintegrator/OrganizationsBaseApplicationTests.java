package ru.bellintegrator;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationsBaseApplicationTests {

    @LocalServerPort
    int randomServerPort;

    RestTemplate restTemplate = new RestTemplate();

    /**
     * Проверка получения перечня стран
     * */
    @Test
    public void countryAccessTest() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/countries";
        URI uri = new URI(baseUrl);
        Map<String, String> parts = new HashMap<>();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, parts, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("Россия"));

    }

    /**
     * Проверка получения перечня типов документов
     * */
    @Test
    public void docTypeAccessTest() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/docs";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, null, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("21"));

    }

    /**
     * Проверка получения организации по id
     * */
    @Test
    public void organizationTest() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/organization/1";
        URI uri = new URI(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("params", headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        Assert.assertTrue(result.getBody().contains("MTS"));
    }

}
