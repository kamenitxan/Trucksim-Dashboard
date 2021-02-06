package cz.kamenitxan.etsdash

import cz.kamenitxan.etsdash.task.TelemetryUpdateTask
import cz.kamenitxan.jakon.JakonInit
import cz.kamenitxan.jakon.core.task.TaskRunner
import spark.{Filter, Request, Response, Spark}


/**
 * Created by TPa on 21.08.18.
 */
class RKeyApp extends JakonInit {


	override def daoSetup(): Unit = {

	}


	routesSetup = () => {
		Spark.before("*", new Filter {
			override def handle(request: Request, response: Response): Unit = {
				response.header("Access-Control-Allow-Origin", "*")
				response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
			}
		})
	}

	override def taskSetup(): Unit = {
		super.taskSetup()
		TaskRunner.registerTask(new TelemetryUpdateTask)
	}
}
