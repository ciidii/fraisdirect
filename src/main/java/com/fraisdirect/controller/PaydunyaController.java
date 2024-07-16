package com.fraisdirect.controller;

import com.fraisdirect.service.PaydunyaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/payments")
public class PaydunyaController {

    private final PaydunyaService payDunyaService;

    @Autowired
    public PaydunyaController(PaydunyaService payDunyaService) {

        this.payDunyaService = payDunyaService;
    }

    @PostMapping("/create-invoice")
    public String createInvoice(@RequestBody Map<String, Object> invoiceData) {
        return payDunyaService.createInvoice(invoiceData);
    }

}
