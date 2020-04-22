trait Query extends Enumeration {

  def setRepos(repos: Repository.Value): Query

  def setLanguages(languages: List[ProgramingLanguages.Language] ) : Query

  def setCommits(amount: _ => Boolean) : Query

}