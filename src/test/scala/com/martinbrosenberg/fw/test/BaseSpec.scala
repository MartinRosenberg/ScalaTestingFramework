package com.martinbrosenberg.fw.test

import java.util.concurrent.TimeUnit

import com.martinbrosenberg.fw.Dsl
import com.martinbrosenberg.fw.wait.Wait
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.scalatest._

import scala.language.reflectiveCalls

abstract class BaseSpec extends FreeSpec
  with BeforeAndAfterEach
  with BeforeAndAfterAll
  with Inside
  with Inspectors
  with Matchers
  with OptionValues
  with Dsl
{

  implicit var driver: WebDriver = _
  implicit var waits: Wait = _

  override def beforeEach(): Unit = {
    val capabilities = new DesiredCapabilities()
    capabilities.setJavascriptEnabled(true)
    driver = new FirefoxDriver(capabilities)
    driver.manage.timeouts.implicitlyWait(10, TimeUnit.SECONDS)
    waits = new Wait
  }

  override def afterEach(): Unit = driver.quit()

}
