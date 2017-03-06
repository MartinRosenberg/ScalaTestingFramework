package com.martinbrosenberg.fw

import com.martinbrosenberg.fw.element.Element
import com.martinbrosenberg.fw.wait.Wait
import org.openqa.selenium.{By, WebDriver}

import scala.collection.JavaConverters._
import scala.language.reflectiveCalls

trait Dsl {

  def driver: WebDriver

  /** Because Scala classes all extend java.lang.Object, which defines a final `wait`, we have to use a different name.
    *
    * todo A better name would be great.
    */
  def waits: Wait

  // todo return an Option[Element]?
  def find(by: By): Element = {
    // todo This is having difficulty finding the implicit Wait, maybe just save it for when DI is in
    //waits.untilReady()
    new Element(driver findElement by)
  }

  // todo return an Option[List[Element]]?
  def findAll(by: By): List[Element] = {
    // todo This is having difficulty finding the implicit Wait, maybe just save it for when DI is in
    //waits.untilReady()
    (driver findElements by).asScala.toList map (new Element(_))
  }

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

  }

}
