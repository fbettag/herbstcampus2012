package code 
package snippet 

import scala.xml._
import net.liftweb.util._
import Helpers._

import net.liftweb.http._
import net.liftweb.http.js.jquery.JqJsCmds._
import net.liftweb.http.js.JsCmds._


object FormExample1 {
	private def doSomething(a: String) = Alert("server side processed: " + a)
	def render = "#example1" #> SHtml.ajaxText("", doSomething _, "placeholder" -> "amaze me!")
}

object FormExample2 {
	private def validate(a: String) = a match {
		case "" => Alert("Leer :(")
		case _ if a.length < 4 => Alert("zu kurz :/")
		case _ if a.length > 20 => Alert("zuuuu lang!")
		case _ => AppendHtml("example2-output", <li>{a}</li>)
	}
	def render = "#example2" #> SHtml.ajaxText("", validate _, "placeholder" -> "validate me!")
}

object FormExample3 {
	private def validate(a: String) = AppendHtml("example3-output", <li>{Unparsed(a)}</li>)
	def render = "#example3" #> SHtml.ajaxText("", validate _, "placeholder" -> "validate me!")
}

class FormExample4 {
	private var name = ""
	private var active = false

	def render =
		".name" #> SHtml.text(name, name = _) &
		".active" #> SHtml.checkbox(active, active = _) &
		".submit" #> SHtml.ajaxSubmit(S ? "submit", () => Alert("name: %s, active: %s".format(name, active.toString)))
}
