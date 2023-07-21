package com.paywallet.POJOClasses;


import java.util.List;

public class CCRegisterAPI {

     String employerId;
     List<String> profiles;
     String context;
     CCCustomer customer;

     CCIdentity identity;
     CCDepositAllocation depositAllocation;
     CCCallbackURLs callbackURLs;

     public CCRegisterAPI(String employerId, List<String> profiles, String context, CCCustomer customer, CCIdentity identity,
                          CCDepositAllocation depositAllocation, CCCallbackURLs callbackURLs)
     {
          this.employerId = employerId;
          this.profiles = profiles;
          this.context = context;
          this.customer = customer;

          this.identity = identity;
          this.depositAllocation = depositAllocation;
          this.callbackURLs = callbackURLs;
     }

     public String getEmployerId() {
          return employerId;
     }

     public void setEmployerId(String employerId) {
          this.employerId = employerId;
     }

     public List<String> getProfiles() {
          return profiles;
     }

     public void setProfiles(List<String> profiles) {
          this.profiles = profiles;
     }

     public String getContext() {
          return context;
     }

     public void setContext(String context) {
          this.context = context;
     }

     public CCCustomer getCustomer() {
          return customer;
     }

     public void setCustomer(CCCustomer customer) {
          this.customer = customer;
     }

     public CCIdentity getIdentity() {
          return identity;
     }

     public void setIdentity(CCIdentity identity) {
          this.identity = identity;
     }

     public CCDepositAllocation getDepositAllocation() {
          return depositAllocation;
     }

     public void setDepositAllocation(CCDepositAllocation depositAllocation) {
          this.depositAllocation = depositAllocation;
     }

     public CCCallbackURLs getCallbackURLs() {
          return callbackURLs;
     }

     public void setCallbackURLs(CCCallbackURLs callbackURLs) {
          this.callbackURLs = callbackURLs;
     }
}
