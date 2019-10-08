package com.thoughtmechanix.licenses.hystrix;

import com.thoughtmechanix.licenses.utils.UserContext;
import com.thoughtmechanix.licenses.utils.UserContextHolder;
import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<V> implements Callable {

  private final Callable<V> delegate;
  private UserContext originalUserContext;

  public DelegatingUserContextCallable(Callable<V> delegate, UserContext originalUserContext) {
    this.delegate = delegate;
    this.originalUserContext = originalUserContext;
  }

  public static <V> Callable<V> create(Callable<V> delegate, UserContext originalUserContext) {
    return new DelegatingUserContextCallable<>(delegate, originalUserContext);
  }

  @Override
  public V call() throws Exception {
    UserContextHolder.setContext(originalUserContext);

    try {
      return delegate.call();
    } finally {
      originalUserContext = null;
    }
  }
}
