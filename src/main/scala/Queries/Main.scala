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
  //github.graphQL connection
  val githubObject = (new Github).withAuthCode(client_data.GetAuthCodeFromConfig()).build


  val MyRepoQuery = GithubQuery[QueryInfo]().withQueryType(MyRepos).withFilter(RepoFilters.includeLanguages(List(Python)))
  val myContributedToReposQuery = GithubQuery[QueryInfo]().withQueryType(MyContributedToRepos).withFilter(RepoFilters.includeLanguages(List(Java,Scala)))

  val MyReposList = githubObject.flatMap(MyRepoQuery.build)
  val MyContributedToList = githubObject.flatMap(myContributedToReposQuery.build)

  val ReposList = MyReposList.get
  val ContributedReposList = MyContributedToList.get


  for (x <- ReposList){
    println( "My Repo: " + x.repoName)
  }

  for (x <- ContributedReposList){
    println("Contributed Repo: " + x.repoName)
  }

}
