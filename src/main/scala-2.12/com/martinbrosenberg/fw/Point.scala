package com.martinbrosenberg.fw

import scala.language.implicitConversions

case class Point(x: Int, y: Int) {

  def moveBy(xOffset: Int, yOffset: Int) = Point(x + xOffset, y + yOffset)

  def moveTo(newX: Int, newY: Int) = Point(newX, newY)

  override def toString: String = s"($x, $y)"

  /** Assuming x,y rarely exceed 4096 pixels, shifting by 12 should provide a good hash value. */
  override def hashCode: Int = x << 12 + y

}

object Point {

  implicit def selenium2Dsl(p: org.openqa.selenium.Point): Point = Point(p.getX, p.getY)

  implicit def dsl2Selenium(p: Point): org.openqa.selenium.Point = new org.openqa.selenium.Point(p.x, p.y)

}
