package cz.kamenitxan.etsdash.pagelet

import cz.kamenitxan.etsdash.JavaRobotExample
import cz.kamenitxan.etsdash.telemetry.TelemetryData
import cz.kamenitxan.jakon.core.dynamic.entity.AbstractJsonResponse
import cz.kamenitxan.jakon.core.dynamic.{AbstractJsonPagelet, Get, JsonPagelet}
import spark.{Request, Response}

import java.sql.Connection

/**
 * Created by TPa on 06.02.2021.
 */
@JsonPagelet(path = "/api")
class TelemetryDataPagelet extends AbstractJsonPagelet{

	@Get(path = "/data")
	def getData(req: Request, res: Response): AbstractJsonResponse = {
		new TelemetryDataResponse(TelemetryDataPagelet.data)
	}

	@Get(path = "/wipers")
	def wipers(req: Request, res: Response): AbstractJsonResponse = {
		JavaRobotExample.inst.`type`("p")
		null
	}
}

object TelemetryDataPagelet {
	var data: TelemetryData = _
}