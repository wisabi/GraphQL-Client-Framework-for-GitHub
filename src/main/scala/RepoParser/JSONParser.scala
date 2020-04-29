package RepoParser
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse

object JSONParser {


  case class R00tJsonObject(data: Data)

  case class Repo(
                   Owner_and_Repo: String,
                   Created: String,
                   Last_Pushed: String,
                   Description: String,
                   Disk_Usage: Double,
                   forks: Forks,
                   pullRequests: Forks,
                   LanguagesUsed: LanguagesUsed
                 )

  case class ListOfRepos(Repo: Repo)

  case class Users_Own_Repos(ListOfRepos: List[ListOfRepos])

  case class Viewer(Users_Own_Repos: Users_Own_Repos, Contributed_To_Repos: Users_Own_Repos)

  case class Data(viewer: Viewer)

  case class Forks(totalCount: Double)

  case class ProgramingLanguage(Language: String)

  case class ListOfLanguages(ProgramingLanguage: ProgramingLanguage)

  case class LanguagesUsed(ListOfLanguages: List[ListOfLanguages])




//////////////////////////////////////////////////////////////////////////////

  case class Collaborators (name: String,loginName: String)

  case class CollaboratorsConnection (totalCount: Int,collaborators: Seq[Collaborators])

  case class Data (viewer: Viewer)

  case class Forks (totalCount: Int)

  case class LanguagesConnection (programingLanguages: Seq[PrimaryLanguage])

  case class Owner (loginName: String)

  case class PrimaryLanguage (language: String)

  case class PullRequestsConnection (totalCount: Int, pullRequestsList: Seq[])/////////////

  case class Repo ( repoName: String,
                    nameWithOwner: String,
                    createdDate: String,
                    lastPushed: String,
                    description: String,
                    primaryLanguage: PrimaryLanguage,
                    owner: Owner,
                    forks: Forks,
                    languagesConnection: LanguagesConnection,
                    stargazersConnection: Forks,
                    collaboratorsConnection: CollaboratorsConnection,
                    pullRequestsConnection: PullRequestsConnection,
                    commitComments: Forks
                  )

  case class RepositoriesConnections (Repo: Seq[Repo])

  case class RootInterface (data: Data)

  case class Viewer (RepositoriesConnections: RepositoriesConnections)











  //Input JSON string, outputs list of repos.
  def getRepoList(string: String): List[ListOfRepos] = {

    //Parse JSON
    implicit val formats = DefaultFormats
    val jsValue = parse(string)

    //Builds a tree of the JSON and parses it into R00tJsonObject class.
    val p = jsValue.extract[R00tJsonObject]

    //Gets list of jacob's own repos.
    p.data.viewer.Users_Own_Repos.ListOfRepos




  }

  //Input JSON string, outputs list of repos.
  def getUserOwnRepo(string: String): List[ListOfRepos] = {

    //Parse JSON
    implicit val formats = DefaultFormats
    val jsValue = parse(string)

    //Builds a tree of the JSON and parses it into R00tJsonObject class.
    val p = jsValue.extract[RootInterface]

    //Gets list of jacob's own repos.
    p.data.viewer.Users_Own_Repos.ListOfRepos
  }

  //Input JSON string, outputs list of repos.
  def getContributedToRepo(string: String): List[ListOfRepos] = {

    //Parse JSON
    implicit val formats = DefaultFormats
    val jsValue = parse(string)

    //Builds a tree of the JSON and parses it into R00tJsonObject class.
    val p = jsValue.extract[RootInterface]

    //Gets list of jacob's own repos.
    p.data.viewer.Contributed_To_Repos.ListOfRepos
  }
}