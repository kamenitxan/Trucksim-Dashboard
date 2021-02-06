package cz.kamenitxan.etsdash.pagelet

import cz.kamenitxan.etsdash.telemetry.TelemetryData
import cz.kamenitxan.jakon.core.dynamic.entity.{AbstractJsonResponse, ResponseStatus}

/**
 * Created by TPa on 06.02.2021.
 */
class TelemetryDataResponse(data: TelemetryData) extends AbstractJsonResponse(ResponseStatus.success, data) {

}
