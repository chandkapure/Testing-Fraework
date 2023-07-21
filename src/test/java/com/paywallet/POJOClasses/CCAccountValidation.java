package com.paywallet.POJOClasses;

public class CCAccountValidation {
    String bankAba;
    String bankAccountNumber;



    public  CCAccountValidation(String bankAba,String bankAccountNumber)
    {
        this.bankAba=bankAba;
        this.bankAccountNumber=bankAccountNumber;
    }

    public String getBankAba() {
        return bankAba;
    }

    public void setBankAba(String bankAba) {
        this.bankAba = bankAba;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}
