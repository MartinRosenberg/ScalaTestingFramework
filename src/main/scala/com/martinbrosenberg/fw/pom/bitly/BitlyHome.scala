package com.martinbrosenberg.fw.pom.bitly

import com.martinbrosenberg.fw.element.Element
import com.martinbrosenberg.fw.pom.Page
import com.martinbrosenberg.fw.wait.Wait
import org.openqa.selenium.{By, WebDriver}

class BitlyHome(implicit val driver: WebDriver, implicit val waits: Wait) extends Page {

  val button: Element = find(By.id("shorten_btn"))

  val field: Element = find(By.id("shorten_url"))

  val mostRecentLink: Element =
    find(By.cssSelector("ul#most_recent_link a.short-url"))

  val url: String = "http://bitly.com"

}