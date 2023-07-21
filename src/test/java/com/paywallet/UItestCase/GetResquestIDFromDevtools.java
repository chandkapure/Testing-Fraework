package com.paywallet.UItestCase;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.paywallet.Base.BaseClass;
import com.paywallet.SetterAndGetterClasses.Requestid;
import com.paywallet.Utilities.UtilityClass;
import org.openqa.selenium.devtools.v113.network.model.RequestId;
import org.openqa.selenium.devtools.v113.network.Network;
import org.openqa.selenium.devtools.v113.network.model.Response;
import org.testng.annotations.Test;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


public class GetResquestIDFromDevtools extends BaseClass{


    @Test()
    public void useDevtollToAccessNetworktab() throws InterruptedException {
        Requestid reqid = new Requestid();
        getDriver().get(ClientContextURL);
        devtools.createSession();
        final RequestId[] requestIds = new RequestId[1];
        devtools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devtools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            requestIds[0] = responseReceived.getRequestId();
            if(response.getUrl().equalsIgnoreCase("https://services-uat.paywalletllc.com/api/v1/request"))
            {
                System.out.println("Status Code is" + " " +response.getStatus() + " " + "Response URL is"+ " " +response.getUrl());
                String responsebody = (devtools.send(Network.getResponseBody(requestIds[0])).getBody());
                reqid.setReuqestid(responsebody);
            }
        });
        UtilityClass.keyClockLogin();
        String obtainedreqid = reqid.getReuqestid();
        System.out.println("Request Body received outside the lambda method is"+" "+obtainedreqid);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(obtainedreqid, JsonObject.class);
        String ClearRequestID = jsonObject.get("data").getAsJsonObject().get("clearRequestId").getAsString();
        System.out.println("Clear Request ID is "+" "+ClearRequestID);


    }
}
