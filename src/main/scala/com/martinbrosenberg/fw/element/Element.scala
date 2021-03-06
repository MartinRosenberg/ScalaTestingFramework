package com.martinbrosenberg.fw.element

import com.martinbrosenberg.fw.wait.Wait
import org.openqa.selenium._
import org.openqa.selenium.interactions.internal.Coordinates
import org.openqa.selenium.internal.Locatable

import scala.collection.JavaConverters._
import scala.language.implicitConversions

/** Wrapper for WebElement. "Implements" WebElement, Locatable, and
  * WrapsElement, but can't *actually* implement them because part of the
  * purpose of this wrapper is to rename the members.
  *
  * todo extend this for different element types
  * todo extend a trait?
  */
class Element(underlying: => WebElement)/*(implicit waits: Wait)*/ {

  def clear(): Unit = underlying.clear()

  def click(): Unit = underlying.click()

  // todo return an Option[Element]?
  def find(by: By): Element = {
    // todo This is having difficulty finding the implicit Wait, maybe just save
    //  it for when DI is in
    //waits.untilReady()
    new Element(underlying.findElement(by))
  }

  // todo return an Option[List[Element]]?
  def findAll(by: By): List[Element] = {
    // todo This is having difficulty finding the implicit Wait, maybe just save
    //  it for when DI is in
    //waits.untilReady()
    underlying.findElements(by).asScala.toList.map(new Element(_))
  }

  def attribute(name: String): Option[String] =
    Option(underlying.getAttribute(name))

  // todo catch and rethrow ClassCastException? match on isInstanceOf[Locatable]
  //  and return an Option? a Try?
  def coordinates: Coordinates =
    underlying.asInstanceOf[Locatable].getCoordinates

  def cssValue(propertyName: String): Option[String] =
    Option(underlying.getCssValue(propertyName))

  def location: Point = underlying.getLocation

  def rect: Rectangle = underlying.getRect

  // todo is this nullable?
  @throws[WebDriverException]("on failure")
  def screenshotAs[X](target: OutputType[X]): X =
    underlying.getScreenshotAs(target)

  def size: Dimension = underlying.getSize

  def tagName: Option[String] = Option(underlying.getTagName)

  def text: Option[String] = Option(underlying.getText)

  def isDisplayed: Boolean = underlying.isDisplayed

  def isEnabled: Boolean = underlying.isEnabled

  def isSelected: Boolean = underlying.isSelected

  def sendKeys(keysToSend: CharSequence*): Unit =
    underlying.sendKeys(keysToSend: _*)

  def submit(): Unit = underlying.submit()

  def wrappedElement: WebElement = underlying

  implicit def element2WebElement(e: Element): WebElement = underlying

}
