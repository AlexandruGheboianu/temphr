package com.ghb.temphr.config;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by agheboianu on 02.03.2017.
 */
public class WebUtil {
  private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
  private static final String X_REQUESTED_WITH = "X-Requested-With";

  private WebUtil() {
    throw new IllegalAccessError("Utility class");
  }

  public static boolean isAjax(HttpServletRequest request) {
    return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
  }
}
