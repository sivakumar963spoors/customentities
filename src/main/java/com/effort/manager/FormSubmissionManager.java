package com.effort.manager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effort.forminterface.FormSubmissionClient;

@Service
public class FormSubmissionManager {

    @Autowired
    private FormSubmissionClient formSubmissionClient;

    public String fetchSyncedForms(
            Long userId,
            String code,
            String clientVersion,
            int apiLevel,
            String syncRequestId,
            String syncTime,
            String signature,
            Map<String, Object> requestBody) {

        return formSubmissionClient
                .syncFormSubmission(userId, code, clientVersion, apiLevel, syncRequestId, syncTime, signature, requestBody)
                .getBody();
    }
}