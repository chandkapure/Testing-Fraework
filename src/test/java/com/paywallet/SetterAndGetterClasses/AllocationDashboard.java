package com.paywallet.SetterAndGetterClasses;

public class AllocationDashboard {
    String InstallmentAmount;
    String FinanceAmount;
    String RoutingCode;
    String VirtualAccNumber;
    String AllocationStatus;

    public String getInstallmentAmount() {
        return InstallmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        InstallmentAmount = installmentAmount;
    }

    public String getFinanceAmount() {
        return FinanceAmount;
    }

    public void setFinanceAmount(String financeAmount) {
        FinanceAmount = financeAmount;
    }

    public String getRoutingCode() {
        return RoutingCode;
    }

    public void setRoutingCode(String routingCode) {
        RoutingCode = routingCode;
    }

    public String getVirtualAccNumber() {
        return VirtualAccNumber;
    }

    public void setVirtualAccNumber(String virtualAccNumber) {
        VirtualAccNumber = virtualAccNumber;
    }

    public String getAllocationStatus() {
        return AllocationStatus;
    }

    public void setAllocationStatus(String allocationStatus) {
        AllocationStatus = allocationStatus;
    }
}
