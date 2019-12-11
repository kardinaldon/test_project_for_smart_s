package front.exception;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;


public class RestTemplateResponseError implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {

    }


}
