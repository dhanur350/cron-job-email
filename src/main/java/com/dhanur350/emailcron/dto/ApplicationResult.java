package com.dhanur350.emailcron.dto;

public class ApplicationResult {
    private String email;
    private boolean fired;
    private String message;

    public ApplicationResult() { }

    public ApplicationResult(String email, boolean fired, String message) {
        this.email = email;
        this.fired = fired;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
