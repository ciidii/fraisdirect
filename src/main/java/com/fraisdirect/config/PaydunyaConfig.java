package com.fraisdirect.config;
import com.paydunya.neptune.*;

public class PaydunyaConfig {
    private PaydunyaCheckoutStore store;

    public PaydunyaConfig() {
        store = new PaydunyaCheckoutStore();
        store.setName("Site de vente Frais Direct");
        store.setTagline("Toujours Satisfait");
        store.setPhoneNumber("779005957");
        store.setWebsiteUrl("http://www.projetfraisdirect.com");
        store.setLogoUrl("http://www.projetfraisdirect.com/logo.png");
    }

    public PaydunyaCheckoutStore getStore() {
        return store;
    }

}

