package com.martinbrosenberg.fw.element

import org.openqa.selenium._
import org.openqa.selenium.interactions.internal.Coordinates
import org.openqa.selenium.internal.Locatable

import scala.collection.JavaConverters._

/** Wrapper for WebElement. "Implements" WebElement, Locatable, and WrapsElement, but can't *actually* implement them
  * because part of the purpose of this wrapper is to rename the members.
  *
  * todo constructor
  * todo extend a trait?
  * todo implicit toWebElement?
  */
class Element {

  // todo implement
  val underlying: WebElement with Locatable = null

  def clear(): Unit = underlying.clear()

  def click(): Unit = underlying.click()

  // todo return an Option[Element]
  def find(by: By): WebElement = underlying.findElement(by)

  // todo return an Option[List[Element]]
  def findAll(by: By): List[WebElement] = underlying.findElements(by).asScala.toList

  def attribute(name: String): Option[String] = Option(underlying.getAttribute(name))

  def coordinates: Coordinates = underlying.getCoordinates

  def cssValue(propertyName: String): Option[String] = Option(underlying.getCssValue(propertyName))

  // todo make Point case class, with implicit toJava
  def location: Point = underlying.getLocation

  // todo make Rectangle case class, with implicit toJava
  def rect: Rectangle = underlying.getRect

  // todo is this nullable?
  def screenshotAs[X](target: OutputType[X]): X = underlying.getScreenshotAs(target)

  // todo make Dimension case class, with implicit toJava
  def size: Dimension = underlying.getSize

  def tagName: Option[String] = Option(underlying.getTagName)

  def text: Option[String] = Option(underlying.getText)

  def wrappedElement: WebElement = underlying

  def isDisplayed: Boolean = underlying.isDisplayed

  def isEnabled: Boolean = underlying.isEnabled

  def isSelected: Boolean = underlying.isSelected

  def sendKeys(keysToSend: CharSequence*): Unit = underlying.sendKeys(keysToSend: _*)

  def submit(): Unit = underlying.submit()

}