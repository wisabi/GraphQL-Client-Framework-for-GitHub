package Tests

import Queries.{GithubQuery, QueryInfo}
import Queries.RequestType.{MyContributedToRepos, MyRepos, SpecificUser}
import RepoParser.JSONParser.Repo
import gitHubObject.{Github, client_data}

/* testing tool libraries */
import org.scalatest._
import org.scalatest.Matchers._


class QueriesTest extends FunSuite{
  private val gitHub = (new Github).withAuthCode(client_data.GetAuthCodeFromConfig()).build

  /****************************************************************************************************
                          Testing three supported types of queries
   *****************************************************************************************************/
  test("My Repos Query Test"){
    val MyRepoQuery = GithubQuery[QueryInfo]().withQueryType(MyRepos)
    val OptionReposList = gitHub.flatMap(MyRepoQuery.build)
    val ReposList = OptionReposList.get


    OptionReposList shouldBe a [Some[_]]
    ReposList shouldBe a [Seq[Repo]]
  }


  test("My Contributed Repos Query Test"){
    val MyContributedRepoQuery = GithubQuery[QueryInfo]().withQueryType(MyContributedToRepos)
    val OptionReposList = gitHub.flatMap(MyContributedRepoQuery.build)
    val ReposList = OptionReposList.get

    OptionReposList shouldBe a [Some[_]]
    ReposList shouldBe a [Seq[Repo]]
  }

  test("Specific User Query Test"){
    val SpecificUserQuery = GithubQuery[QueryInfo]().withQueryType(SpecificUser).withSpecificUser("wisabi")
    val OptionReposList = gitHub.flatMap(SpecificUserQuery.build)
    val ReposList = OptionReposList.get

    OptionReposList shouldBe a [Some[_]]
    ReposList shouldBe a [Seq[Repo]]
  }
}
