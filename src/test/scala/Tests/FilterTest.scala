package Tests

/* testing tool libraries */
import org.scalatest._
import org.scalatest.Matchers._
/* libraries needed for testing */
import Queries.RepoFilters._
import RepoParser.JSONParser._
import Queries.ProgramingLanguages._


class FilterTest extends FunSuite {
  /*****************************************************************************************************
                          Testing filter functions on a collection of Repos
   *****************************************************************************************************/

  /*************** Creating sequence for testing: Seq[Repo] ***************/
  private val Names = List("Repo1", "Repo2", "Repo3", "Repo4")
  private val nameWithOwner =  List("Alex/Repo1", "Wisam/Repo2", "Jacob/Repo3", "Phantom/Repo4")
  private val created = List("2019-02-04T14:46:23Z", "2019-02-05T14:46:23Z", "2019-02-06T14:46:23Z", "2019-02-07T14:46:23Z")
  private val lastPushed = List("2020-02-04T14:46:23Z", "2020-02-05T14:46:23Z", "2020-02-06T14:46:23Z", "2020-02-07T14:46:23Z")
  private val description = List("API", "framework", "work in progress", null)
  private val primaryLanguage = List(PrimaryLanguage("Java"),PrimaryLanguage("Scala"),PrimaryLanguage("Python"),PrimaryLanguage("CPP"))
  private val owner = List(Owner("Alex"),Owner("Wisam"), Owner("Jacob"),Owner("Phantom"))
  private val forks = List(Forks(2), Forks(3),Forks(4),Forks(5))
  private val languagesConnection = List( LanguagesConnection(Seq(PrimaryLanguage("Java"),PrimaryLanguage("Scala"))), LanguagesConnection(Seq(PrimaryLanguage("Scala"))), LanguagesConnection(Seq(PrimaryLanguage("Python"),PrimaryLanguage("Scala"))), LanguagesConnection(Seq(PrimaryLanguage("Java"),PrimaryLanguage("CPP"))))
  private val stargazersConnection = List(Forks(2), Forks(3),Forks(4),Forks(5))
  private val collaboratorsConnection = List( Some(CollaboratorsConnection(2, Seq(Collaborators ("Wisam","wisbumi"), Collaborators ("Jacob","jsanchez78")))), Some(CollaboratorsConnection(1, Seq(Collaborators ("Jacob","jsanchez78")))), Some(CollaboratorsConnection(1, Seq(Collaborators ("Alex","jalomo1197")))), Some(CollaboratorsConnection(0, Seq())))
  private val pullRequestsConnection = List(  PullRequestsConnection(1, Seq(PullRequestsList("Fixed bug", Author("jalomo1197"),"2020-02-04T14:46:23Z"))), PullRequestsConnection(1, Seq(PullRequestsList("Made a Read Me", Author("jsanchez78"),"2020-02-05T14:46:23Z"))), PullRequestsConnection(1, Seq(PullRequestsList("Added test cases", Author("jalomo1197"),"2020-02-06T14:46:23Z"))), PullRequestsConnection(1, Seq(PullRequestsList("Disappered", Author("Phantom"),"2020-02-07T14:46:23Z"))))
  private val commitComments = List(Forks(1), Forks(0),Forks(1),Forks(0))
  // Repos
  private val Repo1 = Repo(Names.head, nameWithOwner.head, created.head, lastPushed.head, description.head, primaryLanguage.head, owner.head, forks.head, languagesConnection.head, stargazersConnection.head,collaboratorsConnection.head, pullRequestsConnection.head,commitComments.head)
  private val Repo2 = Repo(Names(1), nameWithOwner(1), created(1), lastPushed(1), description(1), primaryLanguage(1), owner(1), forks(1), languagesConnection(1), stargazersConnection(1),collaboratorsConnection(1), pullRequestsConnection(1),commitComments(1))
  private val Repo3 = Repo(Names(2), nameWithOwner(2), created(2), lastPushed(2), description(2), primaryLanguage(2), owner(2), forks(2), languagesConnection(2), stargazersConnection(2),collaboratorsConnection(2), pullRequestsConnection(2),commitComments(2))
  private val Repo4 = Repo(Names(3), nameWithOwner(3), created(3), lastPushed(3), description(3), primaryLanguage(3), owner(3), forks(3), languagesConnection(3), stargazersConnection(3),collaboratorsConnection(3), pullRequestsConnection(3),commitComments(3))
  private val RepoSeq = Seq(Repo1, Repo2, Repo3, Repo4)
  /***************************************************************************/


  test("includeLanguages filter test"){
    val RepoFilter = RepoSeq.filter(includeLanguages(List(Scala)))
    RepoFilter.length should be (3)

    val RepoFilter2 = RepoSeq.filter(includeLanguages(List(Python)))
    RepoFilter2.length should be (1)

    val RepoFilter3 = RepoSeq.filter(includeLanguages(List(CPP, Python)))
    RepoFilter3.length should be (2)
  }

  test("MinimumPulls filter test"){
    val RepoFilter = RepoSeq.filter(MinimumPulls(1))
    RepoFilter.length should be (4)

    val RepoFilter2 = RepoSeq.filter(MinimumPulls(2))
    RepoFilter2.length should be (0)
  }

  test("MaxPulls filter test"){
    val RepoFilter = RepoSeq.filter(MaxPulls(1))
    RepoFilter.length should be (4)

    val RepoFilter2 = RepoSeq.filter(MaxPulls(0))
    RepoFilter2.length should be (0)
  }

  test("MinimumForks filter test"){
    val RepoFilter = RepoSeq.filter(MinimumForks(3))
    RepoFilter.length should be (3)

    val RepoFilter2 = RepoSeq.filter(MinimumForks(5))
    RepoFilter2.length should be (1)
  }

  test("MaxForks filter test"){
    val RepoFilter = RepoSeq.filter(MaxForks(3))
    RepoFilter.length should be (2)

    val RepoFilter2 = RepoSeq.filter(MaxForks(5))
    RepoFilter2.length should be (4)
  }

  test("NonNullDescriptions filter test"){
    val RepoFilter = RepoSeq.filter(NonNullDescriptions)
    RepoFilter.length should be (3)
  }

  test("NullDescriptions filter test"){
    val RepoFilter = RepoSeq.filter(NullDescriptions)
    RepoFilter.length should be (1)
  }

  test("createdOnNthDay filter test"){
    assert(createdOnNthDay(2019,2,4)(Repo1))
  }

  test("createdBeforeDate filter test"){
    val before = createdBeforeDate(2020,1,1)(Repo2)
    (before) should be (true)
  }

  test("createdAfterDate filter test"){
    val after = createdAfterDate(2019,1,1)(Repo3)
    (after) should be (true)
  }

}
