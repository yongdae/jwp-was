package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestFactory {
    private static final Logger logger = LoggerFactory.getLogger(RequestFactory.class);

    private RequestFactory() {
    }

    public static Request create(BufferedReader bufferedReader) throws IOException {
        String requestLine = bufferedReader.readLine();
        Map<String, String> header = createHeader(bufferedReader, requestLine);
        return new Request(new RequestLine(requestLine), header, createRequestBody(bufferedReader, header));
    }

    private static Map<String, String> createHeader(BufferedReader bufferedReader, String line) throws IOException {
        Map<String, String> headers = new LinkedHashMap<>();

        while (!line.equals("")) {
            line = bufferedReader.readLine();
            logger.debug("header: {}", line);

            saveHeaderLine(line, headers);
        }

        return headers;
    }

    private static void saveHeaderLine(String line, Map<String, String> headers) {
        if (!line.equals("")) {
            String[] keyValue = line.split(": ");
            setHeader(headers, keyValue);
        }
    }

    private static void setHeader(Map<String, String> headers, String[] keyValue) {
        headers.put(keyValue[0], keyValue[1]);
    }

    private static String createRequestBody(BufferedReader br, Map<String, String> headers) throws IOException {
        int contentLength = getContentLength(headers);
        char[] body = new char[contentLength];
        br.read(body, 0, contentLength);
        return String.copyValueOf(body);
    }

    public static int getContentLength(Map<String, String> headers) {
        return Integer.parseInt(
                headers.getOrDefault("Content-Length", "0")
        );
    }
}
