package com.fraisdirect.controller;

        import com.fraisdirect.service.PaydunyaService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import java.util.Map;
        import com.paydunya.neptune.*;



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
