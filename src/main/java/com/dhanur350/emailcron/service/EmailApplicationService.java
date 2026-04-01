package com.dhanur350.emailcron.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dhanur350.emailcron.dto.ApplicationRequest;
import com.dhanur350.emailcron.dto.ApplicationResult;

@Service
public class EmailApplicationService {

    private static final Logger log = LoggerFactory.getLogger(EmailApplicationService.class);

    private final JavaMailSender mailSender;

    @Value("${app.send-real-email:false}")
    private boolean sendRealEmail;

    public EmailApplicationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public List<ApplicationResult> applyToDeveloperRole(ApplicationRequest request) {
        List<ApplicationResult> results = new ArrayList<>();

        if (request.getEmails() == null || request.getEmails().isEmpty()) {
            throw new IllegalArgumentException("emails array cannot be empty");
        }

        String role = request.getDeveloperRole() == null || request.getDeveloperRole().isBlank()
                ? "Developer" : request.getDeveloperRole().trim();

        String jobDescription = request.getJobDescription() == null || request.getJobDescription().isBlank()
                ? defaultJD(role)
                : request.getJobDescription().trim();

        for (String email : request.getEmails()) {
            if (email == null || email.isBlank()) {
                results.add(new ApplicationResult(email, false, "Invalid email"));
                continue;
            }

            String subject = "Job Application: " + role;
            String body = buildBody(role, jobDescription);

            if (sendRealEmail) {
                try {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(email);
                    msg.setSubject(subject);
                    msg.setText(body);
                    mailSender.send(msg);
                    results.add(new ApplicationResult(email, true, "Email sent"));
                } catch (MailException e) {
                    log.error("Failed to send email to {}", email, e);
                    results.add(new ApplicationResult(email, false, "Failed to send: " + e.getMessage()));
                }
            } else {
                log.info("[SIMULATION] Send to: {} | Subject: {} | Body: {}", email, subject, body);
                results.add(new ApplicationResult(email, true, "Simulated email content generated"));
            }
        }
        return results;
    }

    private String defaultJD(String role) {
        return "As a " + role + ", you will design, develop, test, and document software components. "
                + "Strong collaboration with product and QA teams is required, with continuous improvement mindset.";
    }

    private String buildBody(String role, String jobDescription) {
        return "Hello,\n\n"
                + "I hope this message finds you well. I am applying for the " + role + " role.\n\n"
                + "Job description summary: \n" + jobDescription + "\n\n"
                + "Please find my attached resume and portfolio. I have strong experience in software engineering, "
                + "technical problem solving, and stakeholder communication.\n\n"
                + "Kind regards,\nYour Name";
    }
}
