package com.codewithnaman.controller.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MimeTypeVersioningController {


    @GetMapping(value = "card-list", produces = "application/vnd.com.codewithnaman-v1+json")
    public List<String> getCardTypes() {
        return Arrays.asList("DEBIT", "CREDIT");
    }

    @GetMapping(value = "card-list", produces = "application/vnd.com.codewithnaman-v2+json")
    public List<String> getUpdatedCardTypes() {
        return Arrays.asList("DEBIT", "CREDIT", "PRE-PAID");
    }
}
