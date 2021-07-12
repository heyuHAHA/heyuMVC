package multipart;

import http.HttpMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;

public interface MultipartHttpServletRequest extends HttpServletRequest, MultipartRequest {

    HttpMethod getRequestMethod();

    HttpHeaders getRequestHeaders();

    HttpHeaders getMultipartHeaders(String paramOrFileName);
}
