package servlet;

import exceptions.MultipartException;
import multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

public interface MultipartResolver {

    /**
     *决定一个请求是否含有多媒体内容
     * 通常来说是检查 content type "multipart/form-data"
     * @param request
     * @return
     */
    boolean isMultipart(HttpServletRequest request);

    /**
     * 解析给定的HTTP请求，把它包装成MultipartHttpServletRequest对象
     * 通过标准的ServletRequest方法来访问文件和变量
     * @param request
     * @return
     * @throws MultipartException
     */
    MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException;
}
