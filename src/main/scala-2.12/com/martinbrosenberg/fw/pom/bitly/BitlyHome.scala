package com.martinbrosenberg.fw.pom.bitly

import org.openqa.selenium.{By, WebDriver, WebElement}

class BitlyHome(implicit driver: WebDriver) extends Page {

  def field: WebElement = driver.findElement(By.id("shorten_url"))

  def button: WebElement = driver.findElement(By.id("shorten_btn"))

  def mostRecentLink: WebElement = driver.findElement(By.cssSelector("ul#most_recent_link a.short-url"))

  val url: String = "http://bitly.com"

}