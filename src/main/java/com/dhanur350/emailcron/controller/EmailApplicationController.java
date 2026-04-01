package com.dhanur350.emailcron.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanur350.emailcron.dto.ApplicationRequest;
import com.dhanur350.emailcron.dto.ApplicationResult;
import com.dhanur350.emailcron.service.EmailApplicationService;

@RestController
@RequestMapping("/api/applications")
public class EmailApplicationController {

    private final EmailApplicationService emailApplicationService;

    public EmailApplicationController(EmailApplicationService emailApplicationService) {
        this.emailApplicationService = emailApplicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<List<ApplicationResult>> apply(@RequestBody ApplicationRequest request) {
        List<ApplicationResult> results = emailApplicationService.applyToDeveloperRole(request);
        return ResponseEntity.ok(results);
    }
}
