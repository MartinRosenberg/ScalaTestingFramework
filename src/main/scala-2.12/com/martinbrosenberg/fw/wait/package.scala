package com.martinbrosenberg.fw

import org.openqa.selenium.WebDriver

package object wait {

  type SeFunction[T] = com.google.common.base.Function[WebDriver, T]
  type SePredicate = com.google.common.base.Predicate[WebDriver]

}
