package cz.kamenitxan.etsdash.telemetry


import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

/**
 * Created by TPa on 06.02.2021.
 */
sealed trait Converter[T] {
	def convert(offset: Int)(implicit bb: ByteBuffer): T
}

object Converter {

	case object LocalDateTimeConverter extends Converter[LocalDateTime] {
		override def convert(offset: Int)(implicit bb: ByteBuffer): LocalDateTime = {
			val day = bb.getInt(offset) / 1440 % 7 + 1
			val hour = bb.getInt(offset) % 1440 / 60
			val minute = bb.getInt(offset) % 1440 % 60
			LocalDateTime.of(2021, 2, day, hour, minute)
		}
	}

	case object StringConverter extends Converter[String] {
		var stringSize = 64

		override def convert(offset: Int)(implicit bb: ByteBuffer): String = {
			val dest = new Array[Byte](stringSize)
			bb.position(offset)
			bb.get(dest)

			new String(dest, StandardCharsets.UTF_8).trim
		}
	}

	case object FloatConverter extends Converter[Float] {
		override def convert(offset: Int)(implicit bb: ByteBuffer): Float = {
			bb.getFloat(offset)
		}
	}

	case object BooleanConverter extends Converter[Boolean] {
		override def convert(offset: Int)(implicit bb: ByteBuffer): Boolean = {
			val v = bb.get(offset)
			if (v == 0) {
				false
			} else {
				true
			}
		}
	}
}
