package controllers

import play.api._
import play.api.mvc._

import models._

import com.typesafe.config._
import java.io.File

/**
 * Ajax用 処理
 */
object AjaxController extends Controller {

  /**
   * TokyoMetoro REST API 実行処理
   */
  def ajaxTokyoMetoroAPIExecute(executeType: String) = Action { request =>
  	executeType match {
  		case "" => BadRequest
  		case _  => Ok(JavaHttpRequest.execute(executeType, consumerKey))
  	}
  }

  /**
   * Consumer Key取得
   */
  def consumerKey = {
  	var tokyometoroappConf = Configuration(ConfigFactory.parseFileAnySyntax(new File("conf/tokyometoroapp.conf")))
  	tokyometoroappConf.getString("tokyometoroapp.consumerKey").getOrElse("")
  }

}
