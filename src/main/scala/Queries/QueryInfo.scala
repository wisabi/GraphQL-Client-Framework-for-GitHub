package Queries

import Queries.QueryInfo.Request
import RepoParser.JSONParser.ListOfRepos
import RepoParser.JSONParser
import RequestType.MyRepos
import RequestType.SpecificUser
import RequestType.MyContributedToRepos
import gitHubObject.GHQLResponse

sealed trait QueryInfo

object QueryInfo {
  sealed trait QueryType extends QueryInfo
  sealed trait Request extends QueryInfo

  type EssentialInfo = QueryType
}

case class GithubQuery [I <: QueryInfo ](queryType: RequestType.QueryRequest = MyRepos, userLogin: String = "",filterFunctions: List[ListOfRepos => Boolean] = List() ){

  def withQueryType (queryType: RequestType.QueryRequest) :  GithubQuery[I with QueryInfo.QueryType] =
    this.copy(queryType = queryType)

  def withFilter(f: ListOfRepos => Boolean): GithubQuery[I] = //not strictly needed so no trait to attach
    this.copy(filterFunctions = filterFunctions ++ List(f) )

  def withSpecificUser(userLogin: String): GithubQuery[I] = //not strictly needed so no trait to attach
    this.copy(userLogin = userLogin)

  def build(implicit ev : I =:= QueryInfo.EssentialInfo) : GHQLResponse => Option[List[ListOfRepos]] = {

    //function to return to flatMap
    def GenerateList(Q: Query)(gitHub: GHQLResponse): Option[List[ListOfRepos]] = {
      val json = gitHub.setAndGet(Q.query)
      //TODO: if bad credentials, assert that config file bad key
      //TODO: if query failed, assert that userLogin invalid * every other query works. However, still weak relation *
      val RepoList = JSONParser.getRepoList(json)
      /* filter part */
      var currentSate = RepoList
      for (function <- Q.filterFunctions){
        currentSate = currentSate.filter(function)
      }
      Some(currentSate)
    }

    //modify query if needed
    queryType match {
      case SpecificUser => if (userLogin.equals(""))
                              GenerateList(Query(MyRepos.toString, filterFunctions))
                           else //TODO: userLogin may not be a valid github user, client dependent
                              GenerateList(Query(queryType.toString + userLogin + "\"} }", filterFunctions))
      case MyRepos =>  GenerateList(Query(queryType.toString, filterFunctions))
      case MyContributedToRepos =>  GenerateList(Query(queryType.toString, filterFunctions))
    }
  }
}

case class Query(query: String = "", filterFunctions: List[ListOfRepos => Boolean] = List())
