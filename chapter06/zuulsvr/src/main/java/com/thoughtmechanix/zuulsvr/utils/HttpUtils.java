package com.thoughtmechanix.zuulsvr.utils;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.HttpMethod;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

public class HttpUtils {

  public static String makeUrl(String scheme, String host, String uri) {
    return scheme + "://" + host + uri;
  }

  public static Request makeRequest(String url, HttpServletRequest request) throws IOException {
    Headers headers = makeHeaders(request);
    RequestBody requestBody = makeRequestBody(request, headers);

    return new Request.Builder()
        .url(url)
        .headers(headers)
        .method(request.getMethod(), requestBody)
        .build();
  }

  public static Headers makeHeaders(HttpServletRequest request) {
    Headers.Builder headers = new Headers.Builder();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String name = headerNames.nextElement();
      Enumeration<String> values = request.getHeaders(name);

      while (values.hasMoreElements()) {
        String value = values.nextElement();
        headers.add(name, value);
      }
    }

    return headers.build();
  }

  public static RequestBody makeRequestBody(HttpServletRequest request, Headers headers)
      throws IOException {
    InputStream inputStream = request.getInputStream();
    RequestBody requestBody = null;
    if (inputStream != null && HttpMethod.permitsRequestBody(request.getMethod())) {
      MediaType mediaType = null;
      if (headers.get("Content-Type") != null) {
        mediaType = MediaType.parse(headers.get("Content-Type"));
      }
      requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
    }

    return requestBody;
  }

  public static Response call(Request request) throws IOException {
    return new OkHttpClient().newCall(request).execute();
  }

  public static MultiValueMap<String, String> makeResponseHeaders(Response response) {
    LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
    for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
      responseHeaders.put(entry.getKey(), entry.getValue());
    }

    return responseHeaders;
  }
}
