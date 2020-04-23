package gitHubObject

import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.impl.client.HttpClientBuilder

import scala.io.Source.fromInputStream

object test extends App {
  val test = new gitHubObjectBuilder[gitHubObject](Some(gitHubObject.httpUriRequest), Some(gitHubObject.closeable_connection)).withHTTP(Some(gitHubObject.httpUriRequest)).withClient(Some(gitHubObject.closeable_connection)).build

  //val response = gitHubObject.closeable_connection.execute(gitHubObject.httpUriRequest) //CloseableHttpResponse

  //System.out.println("Response : " + response)

  println(gitHubObject.setAndGet(gitHubObject.gqlReq))

}