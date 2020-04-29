package RepoParser
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse

import scala.annotation.tailrec

object JSONParser {

//
  case class R00tJsonObject(data: Data)
//
//  case class Repo(
//                   Owner_and_Repo: String,
//                   Created: String,
//                   Last_Pushed: String,
//                   Description: String,
//                   Disk_Usage: Double,
//                   forks: Forks,
//                   pullRequests: Forks,
//                   LanguagesUsed: LanguagesUsed
//                 )
//
//  case class ListOfRepos(Repo: Repo)
//
//  case class Users_Own_Repos(ListOfRepos: List[ListOfRepos])
//
//  case class Viewer(Users_Own_Repos: Users_Own_Repos, Contributed_To_Repos: Users_Own_Repos)
//
//  case class Data(viewer: Viewer)
//
//  case class Forks(totalCount: Double)
//
//  case class ProgramingLanguage(Language: String)
//
//  case class ListOfLanguages(ProgramingLanguage: ProgramingLanguage)
//
//  case class LanguagesUsed(ListOfLanguages: List[ListOfLanguages])
//


//////////////////////////////////////////////////////////////////////////////

  case class Collaborators (name: String,loginName: String)

  case class CollaboratorsConnection (totalCount: Int,collaborators: Seq[Collaborators])

  case class Data (viewer: Viewer, user: User)///////////////////////////
  //case class Data (user: User)

  case class Forks (totalCount: Int)

  case class LanguagesConnection (programingLanguages: Seq[PrimaryLanguage])

  case class Owner (loginName: String)

  case class PrimaryLanguage (language: String)

  //case class PullRequestsConnection (totalCount: Int, pullRequestsList: Seq[])/////////////
  //case class ListOfRepos(Repo: Repo)
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

////////////////////////////


  case class Author (userLogin: String)

  //case class Files (path: String)

  //case class FilesConnection (files: Seq[Files])

  case class PullRequestsConnection (totalCount: Int, pullRequestsList: Seq[PullRequestsList])

  case class PullRequestsList (title: String, author: Author,createdDate: String)

  case class User (RepositoriesConnections: RepositoriesConnections)





//  //Input JSON string, outputs list of repos.
//  def getRepoList(string: String): Seq[Repo] = {
//
//    //Parse JSON
//    implicit val formats = DefaultFormats
//    val jsValue = parse(string)
//
//    //Builds a tree of the JSON and parses it into R00tJsonObject class.
//    val p = jsValue.extract[RootInterface]
//
//    //Gets list of jacob's own repos.
//    p.data.viewer.RepositoriesConnections.Repo
//
//  }

  //Input JSON string, outputs list of repos.
  def getUserOwnRepo(string: String): Seq[Repo] = {
    //print("asdfasdjfklasdjfklasj")
    //Parse JSON
    implicit val formats = DefaultFormats
    val jsValue = parse(string)

    //Builds a tree of the JSON and parses it into R00tJsonObject class.
    val p = jsValue.extract[RootInterface]

    //Gets list of jacob's own repos.
    p.data.viewer.RepositoriesConnections.Repo
  }

  //Input JSON string, outputs list of repos.
  def getSpecificUserRepo(string: String): Seq[Repo]= {
    //Parse JSON
    implicit val formats = DefaultFormats
    val jsValue = parse(string)

    //Builds a tree of the JSON and parses it into R00tJsonObject class.
    val p = jsValue.extract[RootInterface]

    //Gets list of jacob's own repos.
    p.data.user.RepositoriesConnections.Repo//.RepositoriesConnections.Repo
  }

  //Traverse through collaborator connections to get list of collaborators
  def getCollaboratorsList(repo:Repo): List[Collaborators] = {
    val collaboratorList: List[Collaborators] = List()

    val c = repo.collaboratorsConnection

    for (e <- c.collaborators){
      collaboratorList.appended(e)
    }
    collaboratorList

  }


}