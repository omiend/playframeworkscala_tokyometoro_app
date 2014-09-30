package controllers

import play.api._, mvc._

/**
 * Ajax用 javascriptRouter
 */
object JsRouter extends Controller {
  
  def javascriptRoutes = Action { implicit request =>
      import routes.javascript._
      Ok(
        Routes.javascriptRouter("jsRoutes")(
            routes.javascript.AjaxController.getRailway
           ,routes.javascript.AjaxController.getStation
           ,routes.javascript.AjaxController.getStationTimetable
           ,routes.javascript.AjaxController.getStationFacility
           ,routes.javascript.AjaxController.getTrain
        )
      ).as("text/javascript")
  }
}
