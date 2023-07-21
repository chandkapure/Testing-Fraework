package com.paywallet.SetterAndGetterClasses;

public class AffordabilityDashboard {
    String requestID;
    String employer;
    String firstname;
    String Lastname;
    String salaryfrequency;
    String netsalarylastpaid;
    String lastsalarypaymentdate;
    String fundsavailabelforallocation;

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

    public String getSalaryfrequency() {
        return salaryfrequency;
    }

    public void setSalaryfrequency(String salaryfrequency) {
        this.salaryfrequency = salaryfrequency;
    }

    public String getNetsalarylastpaid() {
        return netsalarylastpaid;
    }

    public void setNetsalarylastpaid(String netsalarylastpaid) {
        this.netsalarylastpaid = netsalarylastpaid;
    }

    public String getLastsalarypaymentdate() {
        return lastsalarypaymentdate;
    }

    public void setLastsalarypaymentdate(String lastsalarypaymentdate) {
        this.lastsalarypaymentdate = lastsalarypaymentdate;
    }

    public String getFundsavailabelforallocation() {
        return fundsavailabelforallocation;
    }

    public void setFundsavailabelforallocation(String fundsavailabelforallocation) {
        this.fundsavailabelforallocation = fundsavailabelforallocation;
    }

    @Override
    public String toString() {
        return "AffordabilityDashboard{" +
                "requestID='" + requestID + '\'' +
                ", employer='" + employer + '\'' +
                ", firstname='" + firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", salaryfrequency='" + salaryfrequency + '\'' +
                ", netsalarylastpaid='" + netsalarylastpaid + '\'' +
                ", lastsalarypaymentdate='" + lastsalarypaymentdate + '\'' +
                ", fundsavailabelforallocation='" + fundsavailabelforallocation + '\'' +
                '}';
    }
}
