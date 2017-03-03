package com.martinbrosenberg.fw

import com.martinbrosenberg.fw.element.Element
import org.openqa.selenium.{By, WebDriver}

import scala.collection.JavaConverters._

trait Dsl {

  def driver: WebDriver

  // todo return an Option[Element]?
  def find(by: By): Element = new Element(driver findElement by)

  // todo return an Option[List[Element]]?
  def findAll(by: By): List[Element] = (driver findElements by).asScala.toList map (new Element(_))

}
