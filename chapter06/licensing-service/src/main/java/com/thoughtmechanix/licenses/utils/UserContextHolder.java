package com.thoughtmechanix.licenses.utils;

public class UserContextHolder {

  public static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

  public static final UserContext getContext() {
    UserContext  context = userContext.get();

    if (context == null) {
      context = createEmptyContext();
      userContext.set(context);
    }

    return userContext.get();
  }

  public static final UserContext createEmptyContext() {
    return new UserContext();
  }
}
