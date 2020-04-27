package Queries

import Queries.QueryInfo.Request
import RepoParser.JSONParser.ListOfRepos
import RepoParser.JSONParser
import QueryType.AllRepos
import QueryType.UserInfo
import gitHubObject.GHQLResponse

sealed trait QueryInfo

object QueryInfo {
  sealed trait QueryType extends QueryInfo
  sealed trait Request extends QueryInfo

  type EssentialInfo = QueryType
}

case class QueryBuilder [I <: QueryInfo ](query: String = "", filterFunctions: List[ListOfRepos => Boolean] = List() ){

  def withQueryType (queryType: QueryType.Query) :  QueryBuilder[I with QueryInfo.QueryType] =
    this.copy(query = queryType.toString)

  def withFilter(f: ListOfRepos => Boolean): QueryBuilder[I] =
    this.copy(filterFunctions = filterFunctions ++ List(f) )


  def build(implicit ev : I =:= QueryInfo.EssentialInfo) : GHQLResponse => List[ListOfRepos] = {
    //function to return
    def GenerateList(Q: Query)(gitHub: GHQLResponse): List[ListOfRepos] = {
      val json = gitHub.setAndGet(Q.query)
      val RepoList = JSONParser.getRepoList(json)

      /* filter part */
      var currentSate = RepoList
      for (function <- filterFunctions){
        currentSate = currentSate.filter(function)
      }
      currentSate
    }

    //modify query if needed

    //return function partially called
    GenerateList(Query(query, filterFunctions))
  }

}

case class Query(query: String = "", filterFunctions: List[ListOfRepos => Boolean] = List())
