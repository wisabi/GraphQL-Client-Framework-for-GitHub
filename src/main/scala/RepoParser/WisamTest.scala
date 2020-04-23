package RepoParser

import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse

import scala.io.Source.fromInputStream


object WisamTest extends App {



  val BASE_GHQL_URL = "https://api.github.com/graphql"
  val temp = "{viewer {email login url}}"
  val repos = gitHubObject.client_data.repos

  val client = HttpClientBuilder.create.build
  val httpUriRequest = new HttpPost(BASE_GHQL_URL)
  httpUriRequest.addHeader("Authorization", "Bearer 9664303b5648c5c66d68a5b10880dd490c9ac832")
  httpUriRequest.addHeader("Accept", "application/json")
  val gqlReq = new StringEntity("{" +
    "   \"query\":      \"" + repos + "\", " +
    "   \"operationName\": \"ObtainRepos\",  " +
    "   \"variables\":  { \"allRepos\": true } " +
    "}")
  httpUriRequest.setEntity(gqlReq)

  val response = client.execute(httpUriRequest)
  System.out.println("Response : " + response)
  response.getEntity match {
    case null => System.out.println("Response entity is null")
    case x if x != null => {
      val respJson = fromInputStream(x.getContent).mkString
      ////////////////////////////////////////////////////////////////////////////////VVV
      //Case classes for repo object.
      case class Forks(
                        totalCount: Double
                      )
      case class ProgramingLanguage(
                                     Language: String
                                   )
      case class ListOfLanguages(
                                  ProgramingLanguage: ProgramingLanguage
                                )
      case class LanguagesUsed(
                                ListOfLanguages: List[ListOfLanguages]
                              )
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
      case class ListOfRepos(
                              Repo: Repo
                            )
      case class Users_Own_Repos(
                                  ListOfRepos: List[ListOfRepos]
                                )
      case class Viewer(
                         Users_Own_Repos: Users_Own_Repos,
                         Contributed_To_Repos: Users_Own_Repos
                       )
      case class Data(viewer: Viewer)
      case class R00tJsonObject(data: Data)


      implicit val formats = DefaultFormats
      val jsValue = parse(respJson)

      //Builds a tree of the JSON and parses it into R00tJsonObject class.
      val p = jsValue.extract[R00tJsonObject]

      //Gets list of jacob's own repos.
      val l = p.data.viewer.Users_Own_Repos.ListOfRepos

      //prints when each of jacob's repos is created
      for (x <- l){
        println(x.Repo.Created)
      }




    }
  }



}
