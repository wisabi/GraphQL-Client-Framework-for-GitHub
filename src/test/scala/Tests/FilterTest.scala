package Tests
/* testing tool libraries */
import RepoParser.JSONParser.{Author, Collaborators, CollaboratorsConnection, Forks, LanguagesConnection, Owner, PrimaryLanguage, PullRequestsConnection, PullRequestsList, Repo}
import gitHubObject.{Github, client_data}
import org.scalatest._
import org.scalatest.Matchers._


class FilterTest extends FunSuite {
  /*****************************************************************************************************
                          Testing filter functions on a collection of Repos
   *****************************************************************************************************/
  private val Names = List("Repo1", "Repo2", "Repo3", "Repo4")
  private val nameWithOwner =  List("Alex/Repo1", "Wisam/Repo2", "Jacob/Repo3", "Phantom/Repo4")
  private val created = List("2019-02-04T14:46:23Z", "2019-02-05T14:46:23Z", "2019-02-06T14:46:23Z", "2019-02-07T14:46:23Z")
  private val lastPushed = List("2020-02-04T14:46:23Z", "2020-02-05T14:46:23Z", "2020-02-06T14:46:23Z", "2020-02-07T14:46:23Z")
  private val description = List("API", "framework", "", null)
  private val primaryLanguage = List(PrimaryLanguage("Java"),PrimaryLanguage("Scala"),PrimaryLanguage("Python"),PrimaryLanguage("CPP"))
  private val owner = List(Owner("Alex"),Owner("Wisam"), Owner("Jacob"),Owner("Phantom"))
  private val forks = List(Forks(2), Forks(3),Forks(4),Forks(5))
  private val languagesConnection = List( LanguagesConnection(Seq(PrimaryLanguage("Java"),PrimaryLanguage("Scala"))),
                                  LanguagesConnection(Seq(PrimaryLanguage("Scala"))),
                                  LanguagesConnection(Seq(PrimaryLanguage("Python"),PrimaryLanguage("Scala"))),
                                  LanguagesConnection(Seq(PrimaryLanguage("Java"),PrimaryLanguage("CPP")))
                                )
  private val stargazersConnection = List(Forks(2), Forks(3),Forks(4),Forks(5))
  private val collaboratorsConnection = List( Some(CollaboratorsConnection(2, Seq(Collaborators ("Wisam","wisbumi"), Collaborators ("Jacob","jsanchez78")))),
                                      Some(CollaboratorsConnection(1, Seq(Collaborators ("Jacob","jsanchez78")))),
                                      Some(CollaboratorsConnection(1, Seq(Collaborators ("Alex","jalomo1197")))),
                                      Some(CollaboratorsConnection(0, Seq()))
                                    )
  private val pullRequestsConnection = List(  PullRequestsConnection(1, Seq(PullRequestsList("Fixed bug", Author("jalomo1197"),"2020-02-04T14:46:23Z"))),
                                      PullRequestsConnection(1, Seq(PullRequestsList("Made a Read Me", Author("jsanchez78"),"2020-02-05T14:46:23Z"))),
                                      PullRequestsConnection(1, Seq(PullRequestsList("Added test cases", Author("jalomo1197"),"2020-02-06T14:46:23Z"))),
                                      PullRequestsConnection(1, Seq(PullRequestsList("Disappered", Author("Phantom"),"2020-02-07T14:46:23Z"))))
  private val commitComments = List(Forks(1), Forks(0),Forks(1),Forks(0))

  /*************** Creating sequence for testing: Seq[Repo] ***************/
  private val Repo1 = Repo(Names(0), nameWithOwner(0), created(0), lastPushed(0), description(0), primaryLanguage(0), owner(0), forks(0), languagesConnection(0), stargazersConnection(0),collaboratorsConnection(0), pullRequestsConnection(0),commitComments(0))
  private val Repo2 = Repo(Names(1), nameWithOwner(1), created(1), lastPushed(1), description(1), primaryLanguage(1), owner(1), forks(1), languagesConnection(1), stargazersConnection(1),collaboratorsConnection(1), pullRequestsConnection(1),commitComments(1))
  private val Repo3 = Repo(Names(2), nameWithOwner(2), created(2), lastPushed(2), description(2), primaryLanguage(2), owner(2), forks(2), languagesConnection(2), stargazersConnection(2),collaboratorsConnection(2), pullRequestsConnection(2),commitComments(2))
  private val Repo4 = Repo(Names(3), nameWithOwner(3), created(3), lastPushed(3), description(3), primaryLanguage(3), owner(3), forks(3), languagesConnection(3), stargazersConnection(3),collaboratorsConnection(3), pullRequestsConnection(3),commitComments(3))
  private val RepoSeq = Seq(Repo1, Repo2, Repo3, Repo4)
/*
  test("Making Repo List"){
    for (x <- RepoSeq){
      x.getRepoInfo
    }
  }*/

  test("includeLanguages filter test"){

  }

  test("MinimumPulls filter test"){

  }

  test("MaxPulls filter test"){

  }

  test("MinimumForks filter test"){

  }

  test("MaxForks filter test"){

  }

  test("NonNullDescriptions filter test"){

  }

  test("NullDescriptions filter test"){

  }

  test("createdOnNthDay filter test"){

  }

  test("createdBeforeDate filter test"){

  }

  test("createdAfterDate filter test"){

  }
}
