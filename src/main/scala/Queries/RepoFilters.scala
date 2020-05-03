package Queries

import RepoParser.JSONParser.{PrimaryLanguage, Repo}
import java.text.SimpleDateFormat
import java.util.Date

import RepoParser.JSONParser

object RepoFilters {

  def includeLanguages (languages: List[ProgramingLanguages.Language]) (repo: Repo) : Boolean = {
    val Languages: Seq[String] = repo.languagesConnection.programingLanguages.map(x => x.language)
    println(Languages)
    languages.foreach(l => if (Languages.contains(l.toString)) true)
    false
  }

  def MinimumPulls (Amount: Int) (Repo: Repo) : Boolean = {
    Repo.pullRequestsConnection.totalCount >= Amount
  }

  def MaxPulls (Amount: Int) (Repo: Repo) : Boolean = {
    Repo.pullRequestsConnection.totalCount <= Amount
  }


  def MinimumForks (Amount: Int) (Repo: Repo) : Boolean = {
    Repo.forks.totalCount >= Amount
  }

  def MaxForks (Amount: Int) (Repo: Repo) : Boolean = {
    Repo.forks.totalCount <= Amount
  }

  def NonNullDescriptions(Repo: Repo): Boolean = {
    !Repo.description.equals("null")
  }

  def NullDescriptions(Repo: Repo): Boolean = {
    Repo.description.equals("null")
  }
  val format = new SimpleDateFormat("yyyy-MM-dd")
  def validDate(a:Int,b:Int,c:Int): Boolean = {
    (a <= 2020 && a >= 2008 ) && (b >= 1 && b <=12) && (c >=1 && c <= 31)
  }
  def createdOnNthDay (year: Int, month:Int,day:Int)(Repo: Repo): Boolean = {
    val compare:String = year + "-" + month + "-" + day
    if (!validDate(year,month,day)) false
    else {
      val date = format.parse(Repo.createdDate)
      val date2 = format.parse(compare)
      date.equals(date2)
    }
  }
  def createdBeforeDate (year: Int, month:Int,day:Int)(Repo: Repo): Boolean = {
    val compare:String = year + "-" + month + "-" + day
    if (!validDate(year,month,day)) false
    else {
      val date = format.parse(Repo.createdDate)
      val date2 = format.parse(compare)
      date.before(date2)
    }
  }
  def createdAfterDate (year: Int, month:Int,day:Int)(Repo: Repo): Boolean = {
    val compare:String = year + "-" + month + "-" + day
    if (!validDate(year,month,day)) false
    else {
      val date = format.parse(Repo.createdDate)
      val date2 = format.parse(compare)
      date.after(date2)
    }
  }

}
