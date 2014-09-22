package controllers

import play.api._
import play.api.mvc._

import models._

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.HttpURLConnection
import java.net.URL
import org.apache.commons.io.IOUtils


/**
 * Ajax用 処理
 */
object AjaxController extends Controller {

  def ajaxTokyoMetoroAPIExecute(mapCenterLat: String, mapCenterLng: String) = Action { request =>
    Ok(JavaHttpRequest.execute(mapCenterLat, mapCenterLng))
  }

}
