package Queries

import RepoParser.JSONParser.{ListOfRepos, Repo}
import java.text.SimpleDateFormat
import java.util.Date

import RepoParser.JSONParser

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
  val format = new SimpleDateFormat("yyyy-MM-dd")
  def validDate(a:Int,b:Int,c:Int): Boolean = {
    (a <= 2020 && a >= 2008 ) && (b >= 1 && b <=12) && (c >=1 && c <= 31)
  }
  def createdOnNthDay (year: Int, month:Int,day:Int)(Repo: ListOfRepos): Boolean = {
    val compare:String = year + "-" + month + "-" + day
    if (!validDate(year,month,day)) false
    else {
      val date = format.parse(Repo.Repo.Created)
      val date2 = format.parse(compare)
      date.equals(date2)
    }
  }
  def createdBeforeDate (year: Int, month:Int,day:Int)(Repo: ListOfRepos): Boolean = {
    val compare:String = year + "-" + month + "-" + day
    if (!validDate(year,month,day)) false
    else {
      val date = format.parse(Repo.Repo.Created)
      val date2 = format.parse(compare)
      date.before(date2)
    }
  }
  def createdAfterDate (year: Int, month:Int,day:Int)(Repo: ListOfRepos): Boolean = {
    val compare:String = year + "-" + month + "-" + day
    if (!validDate(year,month,day)) false
    else {
      val date = format.parse(Repo.Repo.Created)
      val date2 = format.parse(compare)
      date.after(date2)
    }
  }

}
