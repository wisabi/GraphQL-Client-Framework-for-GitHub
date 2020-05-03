package RepoParser
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse

object JSONParser {


  /****************************************************************************************************
                         Case classes for JSON entries
   *****************************************************************************************************/

  case class Collaborators (name: String,loginName: String)

  case class CollaboratorsConnection (totalCount: Int,collaborators: Seq[Collaborators])

  case class Data (viewer: Viewer, user: User)

  case class Forks (totalCount: Int)

  case class LanguagesConnection (programingLanguages: Seq[PrimaryLanguage])

  case class Owner (loginName: String)

  case class PrimaryLanguage (language: String)

  case class RepositoriesConnections (Repo: Seq[Repo])

  case class RootInterface (data: Data)

  case class Viewer (RepositoriesConnections: RepositoriesConnections)

  case class Author (userLogin: String)

  case class PullRequestsConnection (totalCount: Int, pullRequestsList: Seq[PullRequestsList])

  case class PullRequestsList (title: String, author: Author,createdDate: String)

  case class User (RepositoriesConnections: RepositoriesConnections)

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
                    collaboratorsConnection: Option[CollaboratorsConnection], ///<-----changed this to option
                    pullRequestsConnection: PullRequestsConnection,
                    commitComments: Forks
                  ){

    //Traverse through collaborator connections to get list of collaborators
    def getCollaborators: List[Collaborators] = {
      this.collaboratorsConnection.get.collaborators.toList
    }
    def getPullRequest: List[PullRequestsList] = {
      this.pullRequestsConnection.pullRequestsList.toList
    }
    def getLanguages: List[String] = {
      this.languagesConnection.programingLanguages.toList.map(a => a.language)
    }
    def getStarGazersCount: Int = {
      this.stargazersConnection.totalCount
    }
    def getCommitCommentsCount: Int = {
      this.commitComments.totalCount
    }
    def getRepoInfo: Unit = {

      println("repoName: " + this.repoName + "\nnameWithOwner: " + this.nameWithOwner + "\ncreatedDate: " + this.createdDate + "\nlastPushed: " + this.lastPushed
      + "\ndescription: " + this.description + "\nprimaryLanguage: " + this.primaryLanguage.language + "\nowner: " + this.owner.loginName + "\nforks: "
      + this.forks + "\nlanguages: " + getLanguages + "\nstargazers: " + getStarGazersCount
      + "\ncollaborators: " + getCollaborators + "\npull request: " + getPullRequest + "\ncommit comments: " + getCommitCommentsCount
      )
    }

  }

  /****************************************************************************************************
                         End of Case classes for JSON entries
   *****************************************************************************************************/

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
}