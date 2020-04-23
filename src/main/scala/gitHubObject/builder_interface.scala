package gitHubObject

import sun.security.krb5.internal.AuthorizationData

import scala.concurrent.{ExecutionContext, Future}
//token
//
//httpUriRequest.addHeader("Authorization", "Bearer 9664303b5648c5c66d68a5b10880dd490c9ac832")
trait builder_interface {

  //object user{
   // val temp="{viewer {email login url}}"
  //}
  def setAuthorization(authCode:Option[String]): String = {
    "Bearer " + authCode
  }
  def setHeader(accept:Option[String],format:file_format.Value)

}
