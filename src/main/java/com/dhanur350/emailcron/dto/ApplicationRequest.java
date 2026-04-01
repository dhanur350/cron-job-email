package com.dhanur350.emailcron.dto;

import java.util.List;

public class ApplicationRequest {
    private List<String> emails;
    private String developerRole;
    private String jobDescription;

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getDeveloperRole() {
        return developerRole;
    }

    public void setDeveloperRole(String developerRole) {
        this.developerRole = developerRole;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
