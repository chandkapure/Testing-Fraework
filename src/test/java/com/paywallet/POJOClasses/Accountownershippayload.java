package com.paywallet.POJOClasses;

public class Accountownershippayload {

        private String firstName;
        private String lastName;
        private String bankABA;
        private String bankAccountNumber;
        private String cellPhone;


        public Accountownershippayload(String firstName,String lastName,String bankABA,String bankAccountNumber,String cellPhone)
        {

            this.firstName = firstName;
            this.lastName = lastName;
            this.bankABA = bankABA;
            this.bankAccountNumber = bankAccountNumber;
            this.cellPhone=cellPhone;
        }
        public String getFirstName() {
                return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getBankABA() {
            return bankABA;
        }
        public void setBankABA(String bankABA) {
            this.bankABA = bankABA;
        }
        public String getBankAccountNumber() {
            return bankAccountNumber;
        }
        public void setBankAccountNumber(String bankAccountNumber) {
            this.bankAccountNumber = bankAccountNumber;
        }
        public String getCellPhone() {
            return cellPhone;
        }
        public void setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
        }



}
