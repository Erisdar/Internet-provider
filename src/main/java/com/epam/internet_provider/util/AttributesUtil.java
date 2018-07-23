package com.epam.internet_provider.util;

import com.epam.internet_provider.model.HttpAttribute;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AttributesUtil {

  private HttpServletRequest request;

  public static AttributesUtil getAttributes(HttpServletRequest request) {
    return new AttributesUtil(request);
  }

  @SuppressWarnings("unchecked")
  public <T> T getAttribute(HttpAttribute attribute) {
    return (T) request.getAttribute(attribute.getName());
  }
}
