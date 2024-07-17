package com.fraisdirect.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;


import java.util.Map;

@Service
public class PaydunyaService {

    @Value("${paydunya.master_key}")
    private String masterKey;

    @Value("${paydunya.public_key}")
    private String publicKey;

    @Value("${paydunya.private_key}")
    private String privateKey;

    @Value("${paydunya.token}")
    private String token;

    @Value("${paydunya.api_base_url}")
    private String apiBaseUrl;

    private final RestTemplate restTemplate;

    public PaydunyaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    //creation de facture
    public String createInvoice(Map<String, Object> invoiceData) {
        String url = apiBaseUrl + "/checkout-invoice/create";

        HttpHeaders headers = new HttpHeaders();
        headers.set("PAYDUNYA-MASTER-KEY", masterKey);
        headers.set("PAYDUNYA-PRIVATE-KEY", privateKey);
        headers.set("PAYDUNYA-PUBLIC-KEY", publicKey);
        headers.set("PAYDUNYA-TOKEN", token);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(invoiceData, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}