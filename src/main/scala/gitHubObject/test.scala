package gitHubObject

import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.impl.client.HttpClientBuilder

import scala.io.Source.fromInputStream
import com.typesafe.config.ConfigFactory
import java.io.File

object test extends App {

  val test = new Github[gitHubObject]().withAuthCode(client_data.GetAuthCodeFromConfig()).build

  //println(test.flatMap().setAndGet(client_data.repos))

}