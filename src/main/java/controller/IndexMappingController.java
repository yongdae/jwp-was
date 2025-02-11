package controller;

import webserver.MediaType;
import webserver.Request;
import webserver.RequestLine;
import webserver.Response;

public class IndexMappingController extends RequestMappingControllerAdapter {

    @Override
    public boolean checkUrl(String url) {
        return "/".equals(url);
    }

    @Override
    public Response doGet(Request request) {
        RequestLine requestLine = request.getRequestLine();
        return new Response(MediaType.TEXT_HTML_UTF8, requestLine.getPath(), null);
    }
}
