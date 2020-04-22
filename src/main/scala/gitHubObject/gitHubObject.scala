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

object gitHubObject extends builder_interface {
  sealed trait httpUriRequest extends gitHubObject
  sealed trait client extends gitHubObject


  ///Builder hiding complexity but demanding these traits
  type MandatoryInfo = httpUriRequest with client


  //Fix setHeader
  //override def setHeader(accept: Option[String], format: file_format): Boolean = {
    // accept.get.equals("Accept") && format.equals(file_format.APPJSON)
  //}


}
case class gitHubObjectBuilder[I <: gitHubObject](httpUriRequest:Option[HttpPost],client: Option[CloseableHttpClient],tmp:String){

  val uri = "https://api.github.com/graphql"
  val post = new HttpPost(uri)
  val gqlReq = new StringEntity("{\"query\":\"" + gitHubObject.user.temp + "\"}" )

  post.addHeader("Authorization", "Bearer 9664303b5648c5c66d68a5b10880dd490c9ac832")
  post.addHeader("Accept", "application/json")

  def withHTTP(httpUriRequest:Option[HttpPost]): gitHubObjectBuilder[I with gitHubObject.httpUriRequest] =
    this.copy(httpUriRequest = Some(post))
  def withClient(client: Option[CloseableHttpClient]): gitHubObjectBuilder[I with gitHubObject.client] =
    this.copy(client = Some(HttpClientBuilder.create.build))
  def build(implicit ev: I =:= gitHubObject.MandatoryInfo): _gitHubObjectBuilder =
    _gitHubObjectBuilder(post,client,gqlReq)
}
case class _gitHubObjectBuilder(httpUriRequest:HttpPost,some: Option[CloseableHttpClient],gqlReq:StringEntity){
  httpUriRequest.setEntity(gqlReq)
}
