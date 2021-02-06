package cz.kamenitxan.etsdash.task

import cz.kamenitxan.etsdash.pagelet.TelemetryDataPagelet
import cz.kamenitxan.etsdash.telemetry.Telemetry
import cz.kamenitxan.jakon.core.task.AbstractTask

import java.util.concurrent.TimeUnit

/**
 * Created by TPa on 06.02.2021.
 */
class TelemetryUpdateTask extends AbstractTask(500, TimeUnit.MILLISECONDS) {
	override def start(): Unit = {
		val data = Telemetry.getData
		//println(data)
		TelemetryDataPagelet.data = data
	}
}
