package gitHubObject

import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import sun.security.krb5.internal.AuthorizationData

import scala.concurrent.{ExecutionContext, Future}
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient

sealed trait gitHubObject

object gitHubObject {
  sealed trait HttpUriRequest extends gitHubObject
  sealed trait Client extends gitHubObject


  ///Builder hiding complexity but demanding these traits
  type MandatoryInfo = HttpUriRequest with Client

  def setHeader(accept: Option[String], format: file_format.Value): Unit = {

    client_data.httpUriRequest.addHeader("Authorization", "Bearer 9664303b5648c5c66d68a5b10880dd490c9ac832")
    //if (accept.get.compare("Accept") == 1 & format.equals(file_format.APPJSON))
    client_data.httpUriRequest.addHeader("Accept", "application/json")
    }
}
case class gitHubObjectBuilder[I <: gitHubObject](httpUriRequest:Option[HttpPost] = Some(client_data.httpUriRequest),client: Option[CloseableHttpClient] = Some(client_data.closeable_connection)){
  //Default values

  gitHubObject.setHeader(Some("Accept"),file_format.APPJSON)
  val gqlReq = new StringEntity("{" +
    "   \"query\":      \"" + client_data.repos + "\", " +
    "   \"operationName\": \"ObtainRepos\",  " +
    "   \"variables\":  { \"allRepos\": true } " +
    "}" )
  //val gqlReq = new StringEntity("{\"query\":\"" + client_data.temp + "\"}" )
  client_data.httpUriRequest.setEntity(gqlReq)

  def withHTTP(httpUriRequest:Option[HttpPost]): gitHubObjectBuilder[I with gitHubObject.HttpUriRequest] =
    this.copy(httpUriRequest = httpUriRequest)
  def withClient(client: Option[CloseableHttpClient]): gitHubObjectBuilder[I with gitHubObject.Client] =
    this.copy(client = client)
  def build(implicit ev: I =:= gitHubObject.MandatoryInfo): _gitHubObjectBuilder =
    _gitHubObjectBuilder(client_data.httpUriRequest,gqlReq)
}
case class _gitHubObjectBuilder(httpUriRequest:HttpPost,gqlReq:StringEntity)
