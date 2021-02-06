package cz.kamenitxan.etsdash.telemetry

import java.time.LocalDateTime

/**
 * Created by TPa on 06.02.2021.
 */
case class TelemetryData(
													gameTime: LocalDateTime,
													formattedGameTime: String,
													truckBrand: String,
													truckName: String,
													engineRpm: Float,
													blinkerLeftActive: Boolean,
													blinkerRightActive: Boolean,
													wipers: Boolean,
													parkBrake: Boolean,
												)