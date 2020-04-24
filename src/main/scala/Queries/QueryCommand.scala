package Queries

import QueryType.AllRepos
import QueryType.Query
import ProgramingLanguages.All
import ProgramingLanguages.Language

class QueryCommand(repos: Query = AllRepos, languages: List[Language] = List(All)) extends Query {

 //TODO: get rid of contributed repos
  //

  def setRepos(settingRepos: QueryType.Value): QueryCommand = {
     new QueryCommand(settingRepos)
  }

  def setLanguages(settingLanguages: List[Language]): QueryCommand = {
    new QueryCommand(repos, settingLanguages)
  }

  def setCommits(amount: Function[ _, Boolean]): QueryCommand = {
    new QueryCommand(repos)
  }

  //build : GithubObject => option{}
 // {
    // contection <-- jacob
    // object  <-- spray json  wisam all
    // apply all filter we already
    // return Some(col[repo])
 // }
  override def id: Int = 1
}
