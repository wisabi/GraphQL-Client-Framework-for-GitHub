package Queries

trait Query{

  def setRepos(repos: QueryType.Value): Query

  def setLanguages(languages: List[ProgramingLanguages.Language] ) : Query

  def setCommits(amount: _ => Boolean) : Query

}
