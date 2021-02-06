package cz.kamenitxan.etsdash.telemetry

import cz.kamenitxan.etsdash.telemetry.jna.WindowsService
import cz.kamenitxan.etsdash.telemetry.TelemetryItem.{BlinkerLeftActive, BlinkerRightActive, EngineRpm, GameTime, ParkBrake, TruckBrand, TruckName, Wipers}

import java.nio.{ByteBuffer, ByteOrder}
import java.time.format.DateTimeFormatter

/**
 * Created by TPa on 06.02.2021.
 */
object Telemetry {

	private val ws = new WindowsService
	private val mmf = ws.openMemoryMapFile("Local\\SCSTelemetry")

	private val a = ws.mapViewOfFile(mmf)

	def getData: TelemetryData = {
		val ba = a.getByteArray(0, 32*1024)
		implicit val bb: ByteBuffer = ByteBuffer.wrap(ba)
		bb.order(ByteOrder.LITTLE_ENDIAN)

		val df = DateTimeFormatter.ofPattern("EEEE HH:mm")
		val gameTime = GameTime.convert

		TelemetryData(
			gameTime = gameTime,
			formattedGameTime = df.format(gameTime),
			truckBrand = TruckBrand.convert,
			truckName = TruckName.convert,
			engineRpm = EngineRpm.convert,
			blinkerLeftActive = BlinkerLeftActive.convert,
			blinkerRightActive = BlinkerRightActive.convert,
			wipers = Wipers.convert,
			parkBrake = ParkBrake.convert
		)
	}

}
