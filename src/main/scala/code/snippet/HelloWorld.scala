package code 
package snippet 

import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._


class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  def howdy = "#time *" #> date.map(_.toString)
}

