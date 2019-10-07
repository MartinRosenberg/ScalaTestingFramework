package com.martinbrosenberg.fw.test.bitly

import com.martinbrosenberg.fw.pom.bitly.BitlyHome
import com.martinbrosenberg.fw.test.BaseSpec

/** Test Bitly's branded short URLs for CNN and Buzzfeed. This is exceptionally
  * challenging because Bitly has a Selenium-only bug if you don't wait before
  * clicking the "shorten" button, and Buzzfeed has long-hanging AJAX.
  */
class BitlySpec extends BaseSpec {

  // Not a fan of having to `new` things in a spec, even with the `get` sugar.
  // todo Implement DI.
  val bhp = new BitlyHome

  // Necessary to DRY up tests, but gross.
  // todo Do this better after implementing DI.
  private def getShortUrl(originalUrl: String): String = {
    driver.get(bhp)
    bhp.field.sendKeys(originalUrl + timestamp)

    // this will fail without waiting... but only when doing it with Selenium
    // *scratches head*
    // todo figure out what's making it fail, to avoid thread sleeping
    Thread sleep 2000
    bhp.button click()

    waits.until(
      !bhp.field.attribute("value").getOrElse("").startsWith(originalUrl)
    )
    bhp.field.attribute("value").get
  }

  val testCases = List(
    ("CNN",      "http://www.cnn.com/",       "http://cnn.it/" ),
    ("Buzzfeed", "https://www.buzzfeed.com/", "http://bzfd.it/")
  )

  "The shortened URL starts with the branded short domain for" - {
    testCases.foreach({ case (site, original, short) =>
      site in {
        getShortUrl(original).should(startWith(short))
      }
    })
  }

  "The shortened URL goes to the correct full URL for" - {
    testCases.foreach({ case (site, original, _) =>
      site in {
        val fullUrl = original + timestamp

        driver.get(getShortUrl(original))
        waits.untilDomIsReady()
        driver.getCurrentUrl.should(be(fullUrl))
      }
    })
  }

  "The shortened URL is the same in the field as the history below for" - {
    testCases foreach { case (site, original, _) =>
      site in {
        val shortUrlField = getShortUrl(original)
        waits.until(bhp.mostRecentLink.isDisplayed)
        val shortUrlHistory = bhp.mostRecentLink.text.getOrElse("")
        shortUrlField.should(equal(shortUrlHistory))
      }
    }
  }

}
