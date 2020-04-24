package gitHubObject

import Queries.Main.httpUriRequest
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import sun.security.krb5.internal.AuthorizationData

import scala.concurrent.{ExecutionContext, Future}
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient

import scala.io.Source.fromInputStream

sealed trait gitHubObject

object gitHubObject {
  sealed trait HttpUriRequest extends gitHubObject
  sealed trait Client extends gitHubObject
  sealed trait authCode extends gitHubObject

  val BASE_GHQL_URL = "https://api.github.com/graphql"
  val temp = "{viewer {email login url}}"
  val closeable_connection: CloseableHttpClient = HttpClientBuilder.create.build
  val httpUriRequest = new HttpPost(BASE_GHQL_URL)

  val gqlReq = new StringEntity("{" +
    "   \"query\":      \"" + client_data.repos + "\", " +
    "   \"operationName\": \"ObtainRepos\",  " +
    "   \"variables\":  { \"allRepos\": true } " +
    "}" )
  /// gqlReq => function to set any entity based on given (String)
  // Then execute and return json response

  def setAuthorization(key: String): Unit ={
    ///setAuthorization in builder pattern
    httpUriRequest.addHeader("Authorization", "Bearer " + key)
    httpUriRequest.addHeader("Accept", "application/json")
  }
  def setAndGet(str: String): String = {
    val gqlReq = new StringEntity("{" +
      "   \"query\":      \"" + str + "\", " +
      "   \"operationName\": \"ObtainRepos\",  " +
      "   \"variables\":  { \"allRepos\": true } " +
      "}" )
    httpUriRequest.setEntity(gqlReq)
    val response = closeable_connection.execute(gitHubObject.httpUriRequest)
      response.getEntity match {
      case null => "Response entity is null"
      case x if x != null => {
        fromInputStream(x.getContent).mkString
        //print("respJSON: " + respJson)
        //println(test)
      }
    }
  }
  ///Builder hiding complexity but demanding these traits
  type MandatoryInfo = HttpUriRequest with Client with authCode

  def setHeader(accept: Option[String], format: file_format.Value): Unit = {
   //httpUriRequest.addHeader("Authorization", "Bearer 9664303b5648c5c66d68a5b10880dd490c9ac832")
    //if (accept.get.compare("Accept") == 1 & format.equals(file_format.APPJSON))
    httpUriRequest.addHeader("Accept", "application/json")
    }
  def getData: Option[CloseableHttpResponse] = {
    Some(gitHubObject.closeable_connection.execute(gitHubObject.httpUriRequest)) //CloseableHttpResponse
  }
}
case class gitHubObjectBuilder[I <: gitHubObject](httpUriRequest:Option[HttpPost] = Some(gitHubObject.httpUriRequest),client: Option[CloseableHttpClient] = Some(gitHubObject.closeable_connection),key:String = ""){
  //Default values
  //gitHubObject.setHeader(Some("Accept"),file_format.APPJSON)
  //val gqlReq = new StringEntity("{\"query\":\"" + client_data.temp + "\"}" )
  def withHTTP(httpUriRequest:Option[HttpPost]): gitHubObjectBuilder[I with gitHubObject.HttpUriRequest] =
    this.copy(httpUriRequest = httpUriRequest)
  def withClient(client: Option[CloseableHttpClient]): gitHubObjectBuilder[I with gitHubObject.Client] =
    this.copy(client = client)
  def withAuthCode(key:String): gitHubObjectBuilder[I with gitHubObject.authCode] =
    this.copy(key = key)
  def build(implicit ev: I =:= gitHubObject.MandatoryInfo): _gitHubObjectBuilder =
    _gitHubObjectBuilder(gitHubObject.httpUriRequest,gitHubObject.gqlReq,gitHubObject.setAuthorization(key))
}
case class _gitHubObjectBuilder(httpUriRequest:HttpPost,gqlReq:StringEntity,key:Unit)
