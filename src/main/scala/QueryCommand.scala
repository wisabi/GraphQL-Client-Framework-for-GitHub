import ProgramingLanguages.Language
import ProgramingLanguages.All
import Repository.Repos
import Repository.NoRepos
import Repository.AllRepos


class QueryCommand(repos: Repos = NoRepos, languages: List[Language] = List(All)) extends Query {


  override def setRepos(settingRepos: Repos): QueryCommand = {
     new QueryCommand(settingRepos)
  }

  override def setLanguages(settingLanguages: List[Language]): QueryCommand = {
    new QueryCommand(repos, settingLanguages)
  }

  override def setCommits(amount: Function[ _, Boolean]): QueryCommand = {
    new QueryCommand(repos)
  }



}
