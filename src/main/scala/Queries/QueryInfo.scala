package Queries

import Queries.QueryInfo.Request
import RepoParser.JSONParser.ListOfRepos
import RepoParser.JSONParser
import gitHubObject.gitHubObject
import QueryType.AllRepos
import QueryType.UserInfo

sealed trait QueryInfo

object QueryInfo {
  sealed trait QueryType extends QueryInfo
  sealed trait Request extends QueryInfo

  type EssentialInfo = QueryType with Request
}

case class QueryBuilder [I <: QueryInfo ](queryType: String = "", request: String = "", filterFunctions: List[ListOfRepos => Boolean] = List() ){

  def withQueryType (queryType: QueryType.Value) :  QueryBuilder[I with QueryInfo] =
    this.copy(queryType = queryType.toString)

  def withRequest (request: String) : QueryBuilder[I with Request] =
    this.copy(request = request)

  def withFilter(f: ListOfRepos => Boolean): QueryBuilder[I] =
    this.copy(filterFunctions = filterFunctions ++ List(f) )


  def build(implicit ev : I =:= QueryInfo.EssentialInfo) : gitHubObject => List[ListOfRepos] = {

    def GenerateList(gitHub: gitHubObject): List[ListOfRepos] = {
      val json = gitHubObject.setAndGet("NA")
      val RepoList = JSONParser.getRepoList(json)

      /* filter part */
      var currentSate = RepoList
      for (function <- filterFunctions){
        currentSate = currentSate.filter(function)
      }
      currentSate
    }

    GenerateList
  }
}
