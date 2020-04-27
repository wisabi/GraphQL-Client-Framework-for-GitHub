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
}