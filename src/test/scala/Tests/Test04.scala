package Tests
/* testing tool libraries */
import gitHubObject.{Github, client_data}
import org.scalatest._
import org.scalatest.Matchers._


class Test04 extends FunSuite {
  private val gitHub = (new Github).withAuthCode(client_data.GetAuthCodeFromConfig()).build




}
