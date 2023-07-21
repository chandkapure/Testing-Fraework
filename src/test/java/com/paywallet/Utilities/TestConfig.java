package com.paywallet.Utilities;
import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/Configurations/config.properties"})
public interface TestConfig extends Config{


    @Key("${browsername}.browser")
    String browser();

    @Key("${environment}.CustomerContextURL")
    String CustomerContextURL();
    @Key("${environment}.ClientContextURL")
    String ClientContextURL();
    @Key("${environment}.TokenURL")
    String TokenURL();

    @Key("${environment}.APIURL")
    String APIURL();

    @Key("${environment}.ClientID")
    String ClientID();
    @Key("${environment}.Client_secret")
    String Client_secret();

    @Key("${environment}.Username")
    String Username();
    @Key("${environment}.Password")
    String Password();

    @Key("${environment}.APIKey")
    String APIKey();

    @Key("${environment}.Dashboard")
    String Dashboard();


    String APIGenerateRequestID();
    String APIEmployerTypeAhead();
    String APISelectEmployer();
    String APIRegister();

    String APIUpdate();

//    -------------------------- S3 Bucket Details ---------------
    String S3AccessKey();
    String S3SecretKey();

    String S3Bucketname();

    String S3CustomerContextTestData();

    String S3WrapperAPITestData();

    String S3CodeConvergenceTestData();

    String S3ClientArgyleTestData();

    String S3ClientAtomicFITestData();


//    ---------UI AtomicFI Mail Domain----------------
    String mailosaurIncomeURLAtomicFI();
    String mailosaurEmploymentURLAtomicFI();
    String mailosaurIdentityURLAtomicFI();
    String mailosaurAffordabilityURLAtomicFI();
    String mailosaurcompleteVerificationAtomicFI();
    String mailosaurcompleteAllocationAtomicFI();
    String mailosaurDepositAllocationAtomicFI();


//    --------UI Argyle MailDomain--------------
    String mailosaurIncomeURLArgyle();
    String mailosaurEmploymentURLArgyle();
    String mailosaurIdentityURLArgyle();
    String mailosaurAffordabilityURLArgyle();
    String mailosaurcompleteVerificationArgyle();
    String mailosaurcompleteAllocationArgyle();
    String mailosaurDepositAllocationArgyle();

    String mailosaurverificationDisagree();
    String mailosaurallocationDisagree();
    String mailosaurClientpdsupportfalseallocationArgyle();

    String mailosaurClientpdsupportfalseallocationAtomicFI();

    String mailosaurCustomerpdsupportfalseAllocationArgyle();

    String mailosaurCustomerpdsupportfalseAllocationAtomicFI();

//    -------------API Argyle MailDomains-----------
    String mailosaurAPIIncomeURLArgyle();
    String mailosaurAPIEmploymentURLArgyle();
    String mailosaurAPIIdentityURLArgyle();
    String mailosaurAPIAffordabilityURLArgyle();
    String mailosaurAPIDirectAllocationURLArgyle();
    String mailosaurAPIAccountOwnershipFinicity();
    String mailosaurAPIVerificationRetry();
    String mailosaurAPIAllocationRetry();

    String mailosaurAPIOkinusSkipSDKLoginArgyle();

    String mailosaurAPIOkinusReuserequestArgyle();

    String mailosaurAPIUpdateandCancelArgyle();

    String mailosaurAPILCOEmploymentArgyle();

    String mailosaurAPILCOCancelArgyle();

    String mailosaurAPILCOSkipMultipleLoginArgyle();

    String mailosaurAPIAccountOwnershipLyons();

//    --------------API AtomicFI MailDomains-------------
    String mailosaurAPIIncomeURLAtomicFI();
    String mailosaurAPIEmploymentURLAtomicFI();
    String mailosaurAPIIdentityURLAtomicFI();
    String mailosaurAPIAffordabilityURLAtomicFI();
    String mailosaurAPIDirectAllocationURLAtomicFI();
    String mailosaurAPIUpdateandCancelAtomicFI();
    String mailosaurAPIPDsupportFalseArgyle();
    String mailosaurAPIPDsupportFalseAtomicFI();

    String mailosaurAPIOkinusSkipSDKLoginAtomicFI();

    String mailosaurAPIOkinusReuserequestAtomicFI();

    String mailosaurAPILCOEmploymentAtomicFI();

    String mailosaurAPILCOCancelAtomicFI();

    String mailosaurAPILCOSkipMultipleLoginAtomicFI();

    String mailosaurAPILCORequestReuseAtomicFI();

}
