package com.paywallet.APITestCase;

import com.paywallet.Base.BaseClass;
import com.paywallet.Utilities.CustomDataProvider;
import com.paywallet.Utilities.UtilityClass;
import com.paywallet.Utilities.UtiltyMethodforAPI;
import com.paywallet.pageObject.CC_Customer_ContextFlow;
import org.testng.annotations.Test;

public class TC_CC_AccountOwnership_Lyons extends BaseClass {

    String emailid =  emailstring+ randomNum +"@qcuuzhua.mailosaur.net";

    UtiltyMethodforAPI methodforAPI = new UtiltyMethodforAPI();

    @Test(dataProvider = "CCAPILyons" , dataProviderClass = CustomDataProvider.class)
    public void accountOwnershipFinicity(String employer,String context,String firname,String middlename, String lastname, String bankABA, String bankAccnumber,
                                         String username, String password) throws InterruptedException {

        System.out.println("TC_CC_AccountOwnership_Lyons email id is "+emailid);
        System.out.println("TC_CC_AccountOwnership_Lyons phone number is "+mobile);
//  EmployerType Ahead
        String employerID = methodforAPI.employerTypeAhead(employer);

        String profiles = "ACCOUNT_VALIDATION";

//  Register API
        String requestid = methodforAPI.accountownershipregisterAPI(employerID,profiles,context,firname,middlename ,lastname,emailid,mobile,bankABA,bankAccnumber);

//  SDKLoginStarts

        getDriver().get(APIAccOwnershipLyons);
        UtilityClass.mailosaurLoginandandEmailSelection(username,password);
        UtilityClass.ClientOTP();
        Thread.sleep(2000);
        CC_Customer_ContextFlow customercontext = new CC_Customer_ContextFlow(getDriver());
        logger.info("Entered All the required details in the Homepage and Clicked Submit");
        customercontext.setAccountnumber(bankAccnumber);
        logger.info("Account number Entered Successfully : " + bankAccnumber );
        customercontext.setAbanumber(bankABA);
        logger.info("ABANumber entered Successfully : " + bankABA);
        Thread.sleep(2000);
        customercontext.setSubmit();
        logger.info("Clicked on Submit Successfully");
        UtilityClass.lyonsFlow(bankAccnumber);


    }

}
