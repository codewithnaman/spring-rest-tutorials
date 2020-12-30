package com.codewithnaman.controller.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UriTypeVersioningController {

    @GetMapping("v1/card-list")
    public List<String> getCardTypes(){
        return Arrays.asList("DEBIT","CREDIT");
    }

    @GetMapping("v2/card-list")
    public List<String> getUpdatedCardTypes(){
        return Arrays.asList("DEBIT","CREDIT","PRE-PAID");
    }


}
