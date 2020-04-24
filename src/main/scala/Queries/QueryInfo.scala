package Queries

import Queries.QueryInfo.Request
import gitHubObject.gitHubObject

sealed trait QueryInfo

object QueryInfo {
  sealed trait QueryType extends QueryInfo
  sealed trait Request extends QueryInfo

  type EssentialInfo = QueryType with Request
}

case class QueryBuilder [I <: QueryInfo ](queryType: String = "", request: String = "", filterFunctions: List[repoTest => Boolean] = List() ){

  def withQueryType (queryType: String) :  QueryBuilder[I with QueryInfo] =
    this.copy(queryType = queryType)

  def withRequest (request: String) : QueryBuilder[I with Request] =
    this.copy(request = request)

  def withComparison(f: repoTest => Boolean): QueryBuilder[I] =
    this.copy(filterFunctions = filterFunctions ++ List(f) )

  def build(implicit ev : I =:= QueryInfo.EssentialInfo) : gitHubObject => List[repoTest] = {
    def GenerateList(gitHub: gitHubObject): List[repoTest] = {
      val json = gitHubObject.setAndGet("NA")
      val RepoList = List(new repoTest) // <-- Wisam's function here
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
