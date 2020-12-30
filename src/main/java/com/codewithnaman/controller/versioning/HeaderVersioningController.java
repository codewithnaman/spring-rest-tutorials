package com.codewithnaman.controller.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HeaderVersioningController {

    @GetMapping(value = "card-list", headers = "X-API-VERSION=1")
    public List<String> getCardTypes() {
        return Arrays.asList("DEBIT", "CREDIT");
    }

    @GetMapping(value = "card-list", params = "X-API-VERSION=2")
    public List<String> getUpdatedCardTypes() {
        return Arrays.asList("DEBIT", "CREDIT", "PRE-PAID");
    }
}
