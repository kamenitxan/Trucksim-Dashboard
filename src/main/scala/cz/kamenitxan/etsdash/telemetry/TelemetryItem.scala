package cz.kamenitxan.etsdash.telemetry

import cz.kamenitxan.etsdash.telemetry.Converter.{BooleanConverter, FloatConverter, LocalDateTimeConverter, StringConverter}

import java.nio.ByteBuffer
import java.time.LocalDateTime

/**
 * Created by TPa on 06.02.2021.
 */
sealed trait TelemetryItem[T] {
	val offset: Int
	val converter: Converter[T]

	def convert(implicit bb: ByteBuffer): T = converter.convert(offset)
}

object TelemetryItem {
	val stringSize = 64
	val floatSize = 4
	val boolSize = 1

	val zone4 = 700
	val zone5 = 1500

	case object GameTime extends TelemetryItem[LocalDateTime] {
		override val offset: Int = 64
		override val converter: Converter[LocalDateTime] = LocalDateTimeConverter
	}

	case object TruckBrand extends TelemetryItem[String] {
		override val offset: Int = 2300 + stringSize - 1
		override val converter: Converter[String] = StringConverter
	}

	case object TruckName extends TelemetryItem[String] {
		override val offset: Int = 2300 + stringSize * 3 - 1
		override val converter: Converter[String] = StringConverter
	}

	case object EngineRpm extends TelemetryItem[Float] {
		override val offset: Int = zone4 + (15 + 16 + 24 + 8) * floatSize
		override val converter: Converter[Float] = FloatConverter
	}

	case object BlinkerLeftActive extends TelemetryItem[Boolean] {
		override val offset: Int = zone5 + (16*4 + 14)
		override val converter: Converter[Boolean] = BooleanConverter
	}

	case object BlinkerRightActive extends TelemetryItem[Boolean] {
		override val offset: Int = zone5 + (16*4 + 15)
		override val converter: Converter[Boolean] = BooleanConverter
	}

	case object Wipers extends TelemetryItem[Boolean] {
		override val offset: Int = zone5 + (16*4 + 13) * boolSize
		override val converter: Converter[Boolean] = BooleanConverter
	}

	case object ParkBrake extends TelemetryItem[Boolean] {
		override val offset: Int = zone5 + (16*4 + 2)
		override val converter: Converter[Boolean] = BooleanConverter
	}
}
