package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.utils.SimpleLogger
import scala.util.Failure
import scala.util.Success

class IspCallsTest  extends FlatSpec with Matchers with SimpleLogger {

  "IspList" should "work getting a valid List[MIspResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.ispList(MKey()), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid  MInboundDomainResponse (blocking client)" in {
    MandrillBlockingClient.ispList(MKey()) match {
      case Success(res) =>
        res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispList(MKey(key="invalid")))
  }

  "IspInfo" should "fail if the ip is not valid, with an 'Unknown_IP' code" in {
    MandrillBlockingClient.ispInfo(MIspIp(ip = "123.123.123.123")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 13, "Unknown_IP", "No dedicated IP exists with the address '123.123.123.123'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispInfo(MIspIp(ip = "123.123.123.123", key="invalid")))
  }

  "IspStartWarmup" should "fail if the ip is not valid, with an 'Unknown_IP' code" in {
    MandrillBlockingClient.ispStartWarmup(MIspIp(ip = "123.123.123.123")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 13, "Unknown_IP", "No dedicated IP exists with the address '123.123.123.123'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispStartWarmup(MIspIp(ip = "123.123.123.123", key="invalid")))
  }


  "IspCancelWarmup" should "fail if the ip is not valid, with an 'Unknown_IP' code" in {
    MandrillBlockingClient.ispCancelWarmup(MIspIp(ip = "123.123.123.123")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 13, "Unknown_IP", "No dedicated IP exists with the address '123.123.123.123'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispCancelWarmup(MIspIp(ip = "123.123.123.123", key="invalid")))
  }


  "IspDelete" should "fail if the ip is not valid, with an 'Unknown_IP' code" in {
    MandrillBlockingClient.ispDelete(MIspIp(ip = "123.123.123.123")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 13, "Unknown_IP", "No dedicated IP exists with the address '123.123.123.123'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispDelete(MIspIp(ip = "123.123.123.123", key="invalid")))
  }

  "IspCreatePool" should "work getting a valid MIspInfoPool (async client)" in {
    val res = Await.result(MandrillAsyncClient.ispCreatePool(MIspPoolInfo(pool = "test")), DefaultConfig.defaultTimeout)
    res.name shouldBe "test"
  }
  it should "work getting a valid  MIspInfoPool (blocking client)" in {
    MandrillBlockingClient.ispCreatePool(MIspPoolInfo(pool = "test2")) match {
      case Success(res) =>
        res.name shouldBe "test2"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispCreatePool(MIspPoolInfo(pool = "test", key="invalid")))
  }

  "IspListPool" should "work getting a valid List[MIspInfoPool] (async client)" in {
    val res = Await.result(MandrillAsyncClient.ispListPool(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MIspInfoPool]
  }
  it should "work getting a valid List[MIspInfoPool] (blocking client)" in {
    MandrillBlockingClient.ispListPool(MKey()) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MIspInfoPool]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispListPool(MKey(key="invalid")))
  }

  "IspPoolInfo" should "work getting a valid MIspPoolInfo (async client)" in {
    val res = Await.result(MandrillAsyncClient.ispPoolInfo(MIspPoolInfo(pool = "test")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MIspInfoPool]
  }
  it should "work getting a valid MIspPoolInfo (blocking client)" in {
    MandrillBlockingClient.ispPoolInfo(MIspPoolInfo(pool = "test")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MIspInfoPool]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispPoolInfo(MIspPoolInfo(pool = "test",key="invalid")))
  }

  "IspSetPool" should "work getting a valid MIspResponse (async client)" in {
    MandrillBlockingClient.ispSetPool(MIspSetPool(pool = "test", ip="123.123.123.123", create_pool = false)) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 13, "Unknown_IP", "No dedicated IP exists with the address '123.123.123.123'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }


  "IspProvision" should "fail if the if the account has not paid for it, with a 'PaymentRequired' code" in {
    MandrillBlockingClient.ispProvision(MIspPool(pool = "test", warmup=false)) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 10, "PaymentRequired", "Dedicated IP provisioning is only available for accounts with a positive balance.")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispProvision(MIspPool(pool = "test", warmup=false, key="invalid")))
  }


  "IspDeletePool" should "work getting a valid MIspDeletePoolResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.ispDeletePool(MIspPoolInfo(pool = "test")), DefaultConfig.defaultTimeout)
    res.deleted shouldBe true
  }
  it should "work getting a valid  MIspDeletePoolResponse (blocking client)" in {
    MandrillBlockingClient.ispDeletePool(MIspPoolInfo(pool = "test2")) match {
      case Success(res) =>
        res.deleted shouldBe true
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.ispDeletePool(MIspPoolInfo(pool = "test", key="invalid")))
  }


}
