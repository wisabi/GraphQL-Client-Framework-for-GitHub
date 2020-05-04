package Tests

import Queries.RequestType.MyRepos
import Queries.{GithubQuery, QueryInfo, RequestType}
import RepoParser.JSONParser
import gitHubObject.{GHQLResponse, Github, client_data, gitHubObject}
import jdk.nashorn.internal.parser.JSONParser
import org.scalatest.FunSuite
import org.scalatest.Matchers._
import RepoParser.JSONParser.Repo
class Test03 extends FunSuite{
  val githubObject: Option[GHQLResponse] = new Github[gitHubObject]().withAuthCode(client_data.GetAuthCodeFromConfig()).build

  val other_githubObject: Option[GHQLResponse] = new Github[gitHubObject]().withAuthCode(client_data.GetAuthCodeFromConfig()).build


  test("Builder pattern with phantom types with mandatory info"){

  }
  test("Test githubObjects are created and distinct"){

    assert(githubObject != other_githubObject)
  }

  test("Authorization code is passed from configuration file properly"){
    assert(client_data.GetAuthCodeFromConfig() == "d3e85d5ac6352005b5e708020a09eb08aa7dd6ed")
  }

  test("Get Languages returns valid language per Repo"){


    val MyRepoQuery = GithubQuery[QueryInfo]().withQueryType(MyRepos)
    val MyReposList = githubObject.flatMap(MyRepoQuery.build)
    val ReposList = MyReposList.get

    val test = List("Ruby", "JavaScript", "CSS")
    val actual = ReposList(0).getLanguages



    assert(actual == test)


  }

}
