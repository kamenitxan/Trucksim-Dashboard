package cz.kamenitxan.etsdash

import java.awt.Robot
import java.awt.event.InputEvent

class JavaRobotExample {
	private[kamenitxan] val robot = new Robot

	def leftClick() = {
		robot.mousePress(InputEvent.BUTTON1_MASK)
		robot.delay(200)
		robot.mouseRelease(InputEvent.BUTTON1_MASK)
		robot.delay(200)
	}

	def `type`(i: Int) = {
		robot.delay(40)
		robot.keyPress(i)
		robot.keyRelease(i)
	}

	def `type`(s: String) = {
		val bytes = s.getBytes
		for (b <- bytes) {
			var code: Byte = b
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123)  {
				code = (code - 32).toByte
			}
			robot.delay(40)
			robot.keyPress(code)
			robot.keyRelease(code)
		}
	}
}

object JavaRobotExample {
	val inst = new JavaRobotExample
}