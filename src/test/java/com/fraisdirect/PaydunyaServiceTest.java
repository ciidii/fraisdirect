package com.fraisdirect;
import com.fraisdirect.service.PaydunyaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaydunyaServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private PaydunyaService paydunyaService;
    @Test
    public void testCreateInvoice() {
        // Mocking data for invoice
        Map<String, Object> invoiceData = new HashMap<>();
        invoiceData.put("amount", 1000);
        invoiceData.put("description", "Test de creation de facture");
        // Mocking the response from PayDunya API
        String mockResponse = "{\"status\": \"success\", \"invoice_id\": \"INV123\"}";
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.POST), Mockito.any(), Mockito.eq(String.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));
        // Call the method under test
        String response = paydunyaService.createInvoice(invoiceData);
        // Assertions
        assertEquals(mockResponse, response);
    }
}
