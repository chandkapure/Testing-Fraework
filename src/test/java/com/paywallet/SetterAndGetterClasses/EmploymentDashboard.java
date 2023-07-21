package com.paywallet.SetterAndGetterClasses;

public class EmploymentDashboard {
    String requestID;
    String employer;
    String Lender;
    String firstname;
    String middlename;
    String Lastname;
    String hiredate;
    String typeofemployment;
    String employmentCategory;
    String designation;
    String last4tin;
    String terminationDate;
    String employmentstatus;



    public String getLender() {
        return Lender;
    }

    public void setLender(String lender) {
        Lender = lender;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLast4tin() {
        return last4tin;
    }

    public void setLast4tin(String last4tin) {
        this.last4tin = last4tin;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getTypeofemployment() {
        return typeofemployment;
    }

    public void setTypeofemployment(String typeofemployment) {
        this.typeofemployment = typeofemployment;
    }

    public String getEmploymentCategory() {
        return employmentCategory;
    }

    public void setEmploymentCategory(String employmentCategory) {
        this.employmentCategory = employmentCategory;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getEmploymentstatus() {
        return employmentstatus;
    }

    public void setEmploymentstatus(String employmentstatus) {
        this.employmentstatus = employmentstatus;
    }
}
