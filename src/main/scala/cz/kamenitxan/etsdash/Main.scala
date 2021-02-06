package cz.kamenitxan.etsdash

import cz.kamenitxan.etsdash.telemetry.Telemetry

import java.util.concurrent.{ScheduledThreadPoolExecutor, TimeUnit}


object Main extends App {
	new RKeyApp().run(args)
}
