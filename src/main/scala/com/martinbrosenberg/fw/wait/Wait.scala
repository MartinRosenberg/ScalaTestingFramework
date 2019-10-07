package com.martinbrosenberg.fw.wait

import org.openqa.selenium.support.ui.{FluentWait, WebDriverWait}
import org.openqa.selenium._

/** Wrapper for WebDriverWait. "Implements" org.openqa.selenium.support.ui.Wait,
  * but doesn't *actually* implement it because, honestly, I can't think of a
  * situation in which I would need to use a superclass of WebDriver. It's an
  * interface, and it only extends SearchContext.
  *
  * @param timeout how long to wait for a condition before throwing a
  *                TimeoutException
  * @param sleep how long to wait before checking for the condition again
  */
class Wait(timeout: Int = 10, sleep: Int = 100)
          (implicit val driver: WebDriver) {

  private val underlying: FluentWait[WebDriver] =
    new WebDriverWait(driver, timeout, sleep)
      .ignoring(classOf[NoSuchElementException])
      .ignoring(classOf[WebDriverException])

  private def ajaxIsComplete: Boolean =
    driver.asInstanceOf[JavascriptExecutor]
      .executeScript("return jQuery.active === 0")
      .asInstanceOf[Boolean]

  private def domIsReady: Boolean =
    driver.asInstanceOf[JavascriptExecutor]
      .executeScript("return document.readyState === 'complete'")
      .asInstanceOf[Boolean]

  @throws[TimeoutException]("if the timeout expires")
  def untilAjaxIsComplete(): Unit = until(ajaxIsComplete)

  @throws[TimeoutException]("if the timeout expires")
  def untilDomIsReady(): Unit = until(domIsReady)

  @throws[TimeoutException]("if the timeout expires")
  def until(isTrue: => Boolean): Unit =
    until(new SePredicate {
      override def apply(driver: WebDriver): Boolean = isTrue
    })

  @throws[TimeoutException]("if the timeout expires")
  def until(isTrue: SePredicate): Unit = underlying.until(isTrue)

  @throws[TimeoutException]("if the timeout expires")
  def until[T](isTrue: => T): Option[T] =
    // IntelliJ says to convert this to a SAM, but that doesn't seem to work.
    // todo Figure out how?
    until(new SeFunction[T] {
      override def apply(driver: WebDriver): T = isTrue
    })

  @throws[TimeoutException]("if the timeout expires")
  def until[T](isTrue: SeFunction[T]): Option[T] =
    Option(underlying.until(isTrue))

}
