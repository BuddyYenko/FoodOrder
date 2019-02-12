package com.androiddeft.loginandregistration;

import java.util.Date;


public class User {
    String first_name;
    String last_name;
    Date sessionExpiryDate;

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }
}
