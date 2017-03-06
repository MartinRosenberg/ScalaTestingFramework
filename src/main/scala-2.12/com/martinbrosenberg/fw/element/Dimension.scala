package com.martinbrosenberg.fw.element

import scala.language.implicitConversions

case class Dimension(width: Int, height: Int) {

  /** Assuming width, height rarely exceed 4096 pixels, shifting by 12 should provide a good hash value. */
  override def hashCode: Int = width << 12 + height

  override def toString: String = s"$width x $height"

}

object Dimension {

  implicit def selenium2Dsl(d: SeDimension): Dimension = Dimension(d.width, d.height)

  implicit def dsl2Selenium(d: Dimension): SeDimension = new SeDimension(d.width, d.height)

}
