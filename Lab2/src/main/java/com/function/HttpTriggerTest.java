package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

/**
 * HTTP Trigger for manual testing.
 */
public class HttpTriggerTest {
    @FunctionName("HttpTriggerTest")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.FUNCTION)
            HttpRequestMessage<String> request,
            final ExecutionContext context) {

        String body = request.getBody();
        context.getLogger().info("Received request body: " + body);

        if (body == null || body.isEmpty()) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                          .body("Please pass text in the request body")
                          .build();
        }

        String response = "Simulated blob content processed: " + body;
        context.getLogger().info(response);

        return request.createResponseBuilder(HttpStatus.OK)
                      .body(response)
                      .build();
    }
}
