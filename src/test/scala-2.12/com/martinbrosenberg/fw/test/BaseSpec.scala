package com.martinbrosenberg.fw.test

import java.util.concurrent.TimeUnit

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.scalatest._
import org.scalatest.selenium.WebBrowser

import scala.language.reflectiveCalls

abstract class BaseSpec extends FreeSpec with BeforeAndAfterEach with BeforeAndAfterAll with Inside with Inspectors
  with Matchers with OptionValues with WebBrowser {

  implicit var driver: WebDriver = _

  override def beforeEach(): Unit = {
    driver = new FirefoxDriver()
    driver.manage.timeouts.implicitlyWait(10, TimeUnit.SECONDS)
  }

  override def afterEach(): Unit = driver.quit()

  //region Utilities
  // todo move somewhere more appropriate

  def timestamp: String = System.currentTimeMillis().toString

  implicit class RichDriver(driver: WebDriver) {

    def get[P <: {def url: String}](page: P): P = {
      driver.get(page.url)
      page
    }

    def get[P <: {def url(args: T*): String}, T](page: P, args: T*): P = {
      driver.get(page.url(args: _*))
      page
    }

//    def goTo[P <: {def goTo(): P}](page: P): P = page.goTo()
//
//    def goTo[P <: {def goTo(args: Seq[T]): P}, T](page: P, args: T*): P = page.goTo(args)

    //endregion Utilities

  }

}
