package gitHubObject

import org.apache.http.impl.client.HttpClientBuilder

import scala.io.Source.fromInputStream

object test extends App {
  val test = new gitHubObjectBuilder[gitHubObject](Some(client_data.httpUriRequest), Some(client_data.closeable_connection)).withHTTP(Some(client_data.httpUriRequest)).withClient(Some(client_data.closeable_connection)).build

  println(test)


  /*

  val response = client_data.closeable_connection.execute(client_data.httpUriRequest)
  System.out.println("Response : " + response)
  response.getEntity match {
    case null => System.out.println("Response entity is null")
    case x if x != null => {
      val respJson = fromInputStream(x.getContent).mkString
      print("respJSON: " + respJson)
      println(test)
    }
  }

   */

}