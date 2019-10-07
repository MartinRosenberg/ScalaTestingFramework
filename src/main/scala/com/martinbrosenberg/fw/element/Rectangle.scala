package com.martinbrosenberg.fw.element

import scala.language.implicitConversions

case class Rectangle(x: Int, y: Int, width: Int, height: Int) {

  def point: Point = Point(x, y)

  def dimension: Dimension = Dimension(width, height)

  override def hashCode: Int = 31 * point.hashCode + dimension.hashCode

  override def toString: String = s"$dimension from $point"

}

object Rectangle {

  def apply(p: Point, d: Dimension): Rectangle =
    Rectangle(p.x, p.y, d.width, d.height)

  implicit def selenium2Dsl(r: SeRectangle): Rectangle =
    Rectangle(r.x, r.y, r.width, r.height)

  implicit def dsl2Selenium(r: Rectangle): SeRectangle =
    new SeRectangle(r.x, r.y, r.height, r.width)

}
