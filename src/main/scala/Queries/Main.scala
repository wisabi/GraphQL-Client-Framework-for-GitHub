package Queries

import RepoParser.JSONParser.Repo
import gitHubObject.{Github, client_data}
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
//import QueryInfo
import RequestType.{MyRepos, MyContributedToRepos, SpecificUser}
import ProgramingLanguages.{Java, Scala, HTML, CSS, CPP, Python, JavaScript, All}

import scala.io.Source.fromInputStream

object Main extends App {


  val function = GithubQuery[QueryInfo]().withQueryType(MyRepos).withFilter(RepoFilters.includeLanguages(List(Java,Scala)))
  val githubObject = (new Github).withAuthCode(client_data.GetAuthCodeFromConfig()).build


  val list: Option[Seq[Repo]] = githubObject.flatMap(GithubQuery[QueryInfo]().withQueryType(MyRepos).withFilter(RepoFilters.includeLanguages(List(Java,Scala))).build)
  val list2 = githubObject.flatMap(function.build)

  val realList = list.get
  val newkh = realList.filter(RepoFilters.MaxPulls(10))
  for (x <- realList){
    println(x.repoName)
  }

}
