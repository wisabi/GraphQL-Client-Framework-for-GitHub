package Queries

import RepoParser.JSONParser.ListOfRepos

object RepoFilters {

  def includeLanguages (languages: List[ProgramingLanguages.Value]) (Repo: ListOfRepos) : Boolean = {
    for (language <- languages){
      if (Repo.Repo.LanguagesUsed.ListOfLanguages.contains(language.toString))
        true
    }
    false
  }

  def MinimumPulls (Amount: Double) (Repo: ListOfRepos) : Boolean = {
    Repo.Repo.pullRequests.totalCount >= Amount
  }

  def MaxPulls (Amount: Double) (Repo: ListOfRepos) : Boolean = {
    Repo.Repo.pullRequests.totalCount <= Amount
  }


  def MinimumForks (Amount: Double) (Repo: ListOfRepos) : Boolean = {
    Repo.Repo.forks.totalCount >= Amount
  }

  def MaxForks (Amount: Double) (Repo: ListOfRepos) : Boolean = {
    Repo.Repo.forks.totalCount <= Amount
  }

  def NonNullDescriptions(Repo: ListOfRepos): Boolean = {
    !Repo.Repo.Description.equals("null")
  }

  def NullDescriptions(Repo: ListOfRepos): Boolean = {
    Repo.Repo.Description.equals("null")
  }
}
