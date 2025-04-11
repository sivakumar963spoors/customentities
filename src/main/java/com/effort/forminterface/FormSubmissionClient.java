package com.effort.forminterface;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//@FeignClient(name = "formSubmissionClient", url = "http://35.200.243.184:8080/onlineformsync")
@FeignClient(name = "formSubmissionClient", url = "http://localhost:8081/")
public interface FormSubmissionClient {

    @PostMapping("/v21/api/formSubmission/sync/{userId}")
    ResponseEntity<String> syncFormSubmission(
            @PathVariable("userId") Long userId,
            @RequestParam("code") String code,
            @RequestParam("clientVersion") String clientVersion,
            @RequestParam("apiLevel") int apiLevel,
            @RequestParam("syncRequestId") String syncRequestId,
            @RequestParam("syncTime") String syncTime,
            @RequestParam("signature") String signature,
            @RequestBody Map<String, Object> requestBody
    );
}
