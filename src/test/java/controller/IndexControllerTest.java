package controller;

import http.*;
import org.junit.jupiter.api.Test;
import webserver.*;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

class IndexControllerTest {

    @Test
    void serving() throws IOException, URISyntaxException {
        HttpRequest httpRequest = new HttpRequest(new RequestLine("GET /index.html HTTP/1.1"), null, null);

        HttpResponse httpResponse = RequestControllerContainer.match(httpRequest);

        assertThat(httpResponse.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(httpResponse.getPath()).isEqualTo("/index.html");
        assertThat(httpResponse.getContentType()).isEqualTo(MediaType.TEXT_HTML_UTF8);
    }

}