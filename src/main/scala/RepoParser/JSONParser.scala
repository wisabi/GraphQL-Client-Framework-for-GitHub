package RepoParser
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger

object JSONParser {

  //Getting logger.
  val logger: Logger = Logger(LoggerFactory.getLogger("JSONParser"))
  logger.trace("Executing: JSONParser object")

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
                  ) {

    def getPrimaryLanguage: String = {
      logger.trace("Executing: getPrimaryLanguage")
      if (this.primaryLanguage == null)
        return "No primary language exist"
      this.primaryLanguage.language
    }
    //Traverse through collaborator connections to get list of collaborators
    def getCollaborators: List[Collaborators] = {
      //TODO: if none
      logger.trace("Executing: getCollaborators")
      val empty = List(Collaborators("No collaborators exist", "null"))
      if (this.collaboratorsConnection.get.collaborators.headOption.isEmpty)
        return empty
      this.collaboratorsConnection.get.collaborators.toList
    }
    def getCollaboratorsByLoginName: String = {
      getCollaborators.map(c => c.loginName ).mkString(" , ")
    }
    def getCollaboratorsByName: String = {
      getCollaborators.map(c => c.name ).mkString(" , ")
    }
    def getPullRequestsList: List[PullRequestsList] = {
      logger.trace("Executing: getPullRequest")
      val empty = List(PullRequestsList("No pull request exist", Author(""),""))
      if (this.pullRequestsConnection.pullRequestsList.headOption.isEmpty)
        return empty
      this.pullRequestsConnection.pullRequestsList.toList
    }
    def getPullRequests: String = {
      logger.trace("Executing: getPullRequest")
      getPullRequestsList.map(p => p.title + p.author.userLogin  + p.createdDate).mkString(" , ")
    }
    def getLanguagesList: List[String] = {
      logger.trace("Executing: getLanguages")
      this.languagesConnection.programingLanguages.toList.map(a => a.language)
    }
    def getLanguages: String = {
      logger.trace("Executing: getLanguages")
      getLanguagesList.mkString(" , ")
    }
    def getStarGazersCount: Int = {
      logger.trace("Executing: getStarGazersCount")
      this.stargazersConnection.totalCount
    }
    def getCommitCommentsCount: Int = {
      logger.trace("Executing: getCommitCommentsCount")
      this.commitComments.totalCount
    }
    def getForks: Int = {
      this.forks.totalCount
    }
    def printRepoInfo: Unit = {
      logger.trace("Executing: getRepoInfo")
      println("\nRepository: " + this.repoName + "\nName with owner: " + this.nameWithOwner + "\nDate Created: " + this.createdDate + "\nLast Pushed: " + this.lastPushed
        + "\nDescription: " + this.description + "\nPrimary Language: " + getPrimaryLanguage + "\nOwner: " + this.owner.loginName + "\nNumber of Forks: "
        + getForks + "\nLanguages: " + getLanguages + "\nStar Gazers: " + getStarGazersCount
        + "\nCollaborators: " + getCollaboratorsByLoginName + "\nPull Request: " + getPullRequests + "\nCommit Comments: " + getCommitCommentsCount
      )
    }
  }

  /****************************************************************************************************
                         End of Case classes for JSON entries
   *****************************************************************************************************/

  //Input JSON string, outputs list of repos.
  def getUserOwnRepo(string: String): Seq[Repo] = {
    logger.trace("Executing: getUserOwnRepo")

    implicit val formats = DefaultFormats

    //Parse JSON
    logger.trace("Parsing JSON string")
    val jsValue = parse(string)

    //Builds a tree of the JSON and parses it into R00tJsonObject class.
    logger.trace("Building JSON Tree into case class objects")
    val p = jsValue.extract[RootInterface]

    //Gets list of jacob's own repos.
    logger.trace("Returning list of own repos.")
    p.data.viewer.RepositoriesConnections.Repo
  }

  //Input JSON string, outputs list of repos.
  def getSpecificUserRepo(string: String): Seq[Repo]= {
    logger.trace("Executing: getSpecificUserRepo")

    implicit val formats = DefaultFormats

    //Parse JSON
    logger.trace("Parsing JSON string")
    val jsValue = parse(string)

    //Builds a tree of the JSON and parses it into R00tJsonObject class.
    logger.trace("Building JSON Tree into case class objects")
    val p = jsValue.extract[RootInterface]

    //Gets list of jacob's own repos.
    logger.trace("Returning list of user repos.")
    p.data.user.RepositoriesConnections.Repo//.RepositoriesConnections.Repo
  }
}
