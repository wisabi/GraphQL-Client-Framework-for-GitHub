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

  def withFilter(f: ListOfRepos => Boolean): GithubQuery[I] =
    this.copy(filterFunctions = filterFunctions ++ List(f) )


  def build(implicit ev : I =:= QueryInfo.EssentialInfo) : GHQLResponse => Option[List[ListOfRepos]] = {
    //function to return
    def GenerateList(Q: Query)(gitHub: GHQLResponse): Option[List[ListOfRepos]] = {
      val json = gitHub.setAndGet(Q.query)
      val RepoList = JSONParser.getRepoList(json)

      /* filter part */
      var currentSate = RepoList
      for (function <- Q.filterFunctions){
        currentSate = currentSate.filter(function)
      }
      Some(currentSate)
    }

    //modify query if needed
    if(queryType == SpecificUser){
      if (userLogin.equals(""))

    }

    //return function partially called
    GenerateList(Query(queryType.toString, filterFunctions))
  }

}

case class Query(query: String = "", filterFunctions: List[ListOfRepos => Boolean] = List())
