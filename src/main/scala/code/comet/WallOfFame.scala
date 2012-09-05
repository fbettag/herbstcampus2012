package code.comet

import net.liftweb.actor._
import net.liftweb.http._
import js.jquery.JqJsCmds.PrependHtml
import net.liftweb.http.js.JsCmds._


case class WallEntries(entries: List[String])

object WallOfFameHolder extends LiftActor with ListenerManager {
	private var lines = List("Welcome!")

	def createUpdate = WallEntries(lines)

	override def lowPriority = {
		case a: String =>
			lines ::= a
			lines = lines.take(50)
			updateListeners(a)
	}
}


class WallOfFame extends CometActor with CometListener {
	def registerWith = WallOfFameHolder
	private var lines = List[String]()

	private def jsCmd(we: String) = PrependHtml("wall-output", <li>{we}</li>)

	override def lowPriority = {
		case a: String =>
			lines ::= a
			partialUpdate(jsCmd(a))
		case WallEntries(entries) => lines = entries
			val update = lines.reverse.map(jsCmd)
			partialUpdate(update)
	}

	def submit(a: String) = {
		if (a.trim.length > 2) WallOfFameHolder ! a
		SetValueAndFocus("wall-input", "")
	}

	def render =
		"#wall-input" #> SHtml.ajaxText("", submit _, "placeholder" -> "Write on the wall!") &
		"#wall-output *" #> lines.map(b => <li>{b}</li>)

}