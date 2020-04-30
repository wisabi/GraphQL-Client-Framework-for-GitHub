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

  //val MyRepoQuery = GithubQuery[QueryInfo]().withQueryType(MyRepos)
  //val myContributedToReposQuery = GithubQuery[QueryInfo]().withQueryType(MyContributedToRepos)
  val SpecificUserQuery = GithubQuery[QueryInfo]().withQueryType(SpecificUser).withSpecificUser("wisabi")

  //val MyReposList = githubObject.flatMap(MyRepoQuery.build)
  //val MyContributedToList = githubObject.flatMap(myContributedToReposQuery.build)
  val SpecificUserList = githubObject.flatMap(SpecificUserQuery.build)

  //val ReposList = MyReposList.get
  //val ContributedReposList = MyContributedToList.get
  val UserList = SpecificUserList.get

/*
  println( "\n ----------------------- MyRepos  ----------------------- \n ")
  for (x <- ReposList){
    println("\n\nName: " + x.repoName)
    println("ProgramingLanguages: ")
    for (lan <- x.languagesConnection.programingLanguages){
      println(lan.language)
    }
  }

  println( "\n ----------------------- Contributed  ----------------------- \n ")
  for (x <- ContributedReposList){
    println("\n\nName: " + x.repoName)
    println("ProgramingLanguages: ")
    for (lan <- x.languagesConnection.programingLanguages){
      print(lan.language + " ")
    }
  }*/

  println( "\n ----------------------- User  ----------------------- \n ")
  for (x <- UserList){
    println("\n\nName: " + x.repoName)
    println("ProgramingLanguages: ")
    for (lan <- x.languagesConnection.programingLanguages){
      println(lan.language)
    }
  }

}
