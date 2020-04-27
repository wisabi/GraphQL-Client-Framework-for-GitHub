package Queries

import gitHubObject.{Github, client_data}
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
//import QueryInfo
import QueryType.AllRepos
import ProgramingLanguages.{Java, Scala, HTML, CSS, CPP, Python, JavaScript, All}

import scala.io.Source.fromInputStream

object Main extends App {


  val function = QueryBuilder[QueryInfo]().withQueryType(AllRepos).withFilter(RepoFilters.includeLanguages(List(Java,Scala)))
  val githubObject = (new Github).withAuthCode(client_data.GetAuthCodeFromConfig()).build

  val list = githubObject.flatMap((new QueryBuilder[QueryInfo]()).withQueryType(AllRepos).withFilter(RepoFilters.includeLanguages(List(Java,Scala))).build)
  val list2 = githubObject.flatMap(function.build)

  println(list)

}
