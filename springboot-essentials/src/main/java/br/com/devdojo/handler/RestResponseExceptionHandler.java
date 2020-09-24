package br.com.devdojo.handler;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class RestResponseExceptionHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        System.out.println("Inside hasError");
        return super.hasError(response);

    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("Doing something with status code " + response.getStatusCode());
        System.out.println("Doing something with body " + IOUtils.toString(response.getBody(), StandardCharsets.UTF_8));
//        super.handleError(response);
    }
}
