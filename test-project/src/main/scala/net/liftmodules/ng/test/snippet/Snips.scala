package net.liftmodules.ng
package test.snippet

import Angular._

import scala.xml.NodeSeq
import test.model.Test2Obj
import net.liftweb._
import net.liftweb.common.{Empty, Failure, Full, Loggable}
import util._
import http._
import js._
import Helpers._
import SHtml._
import JsCmds._
import net.liftweb.json.DefaultFormats

/** Defines snippets for testing Angular */
object Snips extends Loggable {
  implicit val formats = DefaultFormats

  def renderPair(xhtml:NodeSeq) = renderIfNotAlreadyDefined(angular.module("SnipServices1")
      .factory("snipServices1", jsObjFactory()

      .defAny("call1", {
        logger.info("call1() received on server")
        Full("FromServer")

    }).defParamToAny("call2", (str:String) => {
        logger.info(s"call2($str) received on server.")
        Full(s"FromServer $str")
    }).defAny("callFail", {
        logger.info("callFail() received on server")
        Failure("FromServerFail")
    }).defAny("callException", {
        logger.info("callFail() received on server")
        throw new Exception("FromServerException")
    })
  ))

  def renderSingle(xhtml:NodeSeq) = renderIfNotAlreadyDefined(angular.module("SnipServices2")
    .factory("snipServices2", jsObjFactory()

    .defParamToAny("call", (obj:Test2Obj) => {
      import obj._
      logger.info(s"call($obj) received on server.")
      Full(Test2Obj(s"FromServer $str1", s"FromServer $str2"))
  })))

  def renderRootScopeBroadcastStringButton = "* [onclick]" #> ajaxInvoke( () => {
    S.session.map { _.sendCometActorMessage("RootScopeBroadcastStringActor", Empty, "start") }
    Noop
  })

  def renderRootScopeBroadcastJsonButton = "* [onclick]" #> ajaxInvoke( () => {
    S.session.map { _.sendCometActorMessage("RootScopeBroadcastJsonActor", Empty, "start") }
    Noop
  })

  def renderRootScopeEmitStringButton = "* [onclick]" #> ajaxInvoke( () => {
    S.session.map { _.sendCometActorMessage("RootScopeEmitStringActor", Empty, "start") }
    Noop
  })

  def renderRootScopeEmitJsonButton = "* [onclick]" #> ajaxInvoke( () => {
    S.session.map { _.sendCometActorMessage("RootScopeEmitJsonActor", Empty, "start") }
    Noop
  })

  def renderScopeEmitButton = "* [onclick]" #> ajaxInvoke( () => {
    S.session.map { _.sendCometActorMessage("ScopeActor", Empty, "emit") }
    Noop
  })

  def renderScopeBroadcastButton = "* [onclick]" #> ajaxInvoke( () => {
    S.session.map { _.sendCometActorMessage("ScopeActor", Empty, "broadcast") }
    Noop
  })

  def renderCometAssignmentButton = "* [onclick]" #> ajaxInvoke( () => {
    S.session.map { _.sendCometActorMessage("AssignmentActor", Empty, "start") }
    Noop
  })

  def renderCometDelayButton = "* [onclick]" #> ajaxInvoke( () => {
    S.session.map { _.sendCometActorMessage("EarlyEmitActor", Empty, "go") }
    Noop
  })

}
