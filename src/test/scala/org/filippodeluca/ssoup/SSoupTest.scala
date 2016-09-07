package org.filippodeluca.ssoup

import SSoup._
import org.jsoup.nodes.{ Node, Element }
import org.scalatest.Matchers
import org.scalatest.FlatSpec

class SSoupTest extends FlatSpec with Matchers {

  val html =
    """
      |<html>
      |  <head>
      |  </head>
      |  <body>
      |    <div id="foo" class="bar>Hello World</div>
      |  </body>
      |</html>
    """.stripMargin

  "SSoup" should "Return RichDocument" in {
    val doc: RichDocument = parse(html)
    doc shouldBe a [RichDocument]
  }

  it should "Return RichElement" in {
    val doc = parse(html)
    val head: RichElement = doc.head
    head shouldBe a [RichElement]
  }

  it should "Return RichElements" in {
    val doc = parse(html)
    val head = doc.head
    val xs: RichElements = head.getElementsByAttributeStarting("foo")
    xs shouldBe a[RichElements]
  }

}
