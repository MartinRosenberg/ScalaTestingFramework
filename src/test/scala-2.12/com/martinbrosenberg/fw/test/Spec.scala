package com.martinbrosenberg.fw.test

import com.martinbrosenberg.fw.pom.bitly.BitlyHome

class Spec extends BaseSpec {

  val testCases = List(
    ("CNN",      "http://www.cnn.com/",      "http://cnn.it/" ),
    ("Buzzfeed", "http://www.buzzfeed.com/", "http://bzfd.it/")
  )

  "The shortened URL is the same in the field as the history below for" - {
    testCases foreach { case (site, original, _) =>
      site in {
        // todo not a fan of having to `new` things in a spec, even with this sugar
        val bhp = driver.get(new BitlyHome)
        bhp.field.sendKeys(original + timestamp)
        // todo this will fail without waiting
        bhp.button.click()
        // todo `getAttribute` and `getText` are very un-Scala-like names -- wrap?
        val shortUrlField = bhp.field.getAttribute("value")
        val shortUrlHistory = bhp.mostRecentLink.getText
        shortUrlField shouldEqual shortUrlHistory
      }
    }
  }

}
