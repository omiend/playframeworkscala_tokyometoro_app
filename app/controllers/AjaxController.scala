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

	val endPoint: String = "https://api.tokyometroapp.jp/api/v2/"

  /**
   * 鉄道路線情報 odpt:Railway
   */
  def getRailway = Action {
    val url = endPoint + "datapoints?rdf:type=odpt:Railway&acl:consumerKey=" + consumerKey;
    JavaHttpRequest.execute(url) match {
      case st: String => Ok(st)
      case _          => BadRequest
    }
  }

  /**
   * 駅情報 odpt:Station
   */
  def getStation(railWay: String) = Action { 
    val url = endPoint + "datapoints?rdf:type=odpt:Station&odpt:railway=" + railWay.replaceAll("_", ":") + "&acl:consumerKey=" + consumerKey;
    JavaHttpRequest.execute(url) match {
      case st: String => Ok(st)
      case _          => BadRequest
    }
  }

  /**
   * 駅時刻表 odpt:StationTimetable
   */
  def getStationTimetable(station: String) = Action { 
    val url = endPoint + "datapoints?rdf:type=odpt:StationTimetable&odpt:station=" + station.replaceAll("_", ":") + "&acl:consumerKey=" + consumerKey;
    JavaHttpRequest.execute(url) match {
      case st: String => Ok(st)
      case _          => BadRequest
    }
  }

  /**
   * 駅施設情報 odpt:StationFacility
   */
  def getStationFacility(station: String) = Action { 
    val url = endPoint + "datapoints?rdf:type=odpt:StationFacility&owl:sameAs=" + station.replaceAll("_", ":") + "&acl:consumerKey=" + consumerKey;
    JavaHttpRequest.execute(url) match {
      case st: String => Ok(st)
      case _          => BadRequest
    }
  }

  /**
   * 列車ロケーション情報 odpt:Train
   */
  def getTrain(railWay: String) = Action { 
    val url = endPoint + "datapoints?rdf:type=odpt:Train&odpt:railway=" + railWay.replaceAll("_", ":") + "&acl:consumerKey=" + consumerKey;
    JavaHttpRequest.execute(url) match {
      case st: String => Ok(st)
      case _          => BadRequest
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
