package Queries

import RepoParser.JSONParser.Repo
import java.text.SimpleDateFormat

/*
   Use: These functions are to be used as the parameter to a  Seq[Repo].filter(...)  call. In which they are partially
        called. This is to apply these functions to a collection of Repos, to filter the Repos a client wants to maintain.
        e.g.
          val l :  Seq[Repo] = ??? <-- GHQLResponse.flatmap(Query ... )
          val newList = l.filter(includedLanguages(List(Java, Scala)) )  <-- obtain the repos that have scala and java code
 */

object RepoFilters {

  // Use to determine if a repo contains at least one the the programming languages in 'languages'
  def includeLanguages (languages: List[ProgramingLanguages.Language]) (repo: Repo) : Boolean = {
    val Languages: Seq[String] = repo.languagesConnection.programingLanguages.map(x => x.language)
    var t = false
    languages.foreach(l => if (Languages.contains(l.toString)){t = true} )
    t
  }

  // Use to determine if a repo contains at least a certain amount of pulls
  def MinimumPulls (Amount: Int) (Repo: Repo) : Boolean = {
    Repo.pullRequestsConnection.totalCount >= Amount
  }

  // Use to determine if a repo contains less or equal to a certain amount of pulls
  def MaxPulls (Amount: Int) (Repo: Repo) : Boolean = {
    Repo.pullRequestsConnection.totalCount <= Amount
  }

  // Use to determine if a repo contains at least a certain amount of forks
  def MinimumForks (Amount: Int) (Repo: Repo) : Boolean = {
    Repo.forks.totalCount >= Amount
  }

  // Use to determine if a repo contains less or equal to a certain amount of forks
  def MaxForks (Amount: Int) (Repo: Repo) : Boolean = {
    Repo.forks.totalCount <= Amount
  }

  // Use to determine if a repo contains a description
  def NonNullDescriptions(Repo: Repo): Boolean = {
    !(Repo.description == null)
  }

  // Use to determine if a repo contains a null description field
  def NullDescriptions(Repo: Repo): Boolean = {
    Repo.description == null
  }

  // Needed for date parsing according to github graphQL date format
  val format = new SimpleDateFormat("yyyy-MM-dd")

  // Use to determine if valid date was entered by client
  def validDate(a:Int,b:Int,c:Int): Boolean = {
    (a <= 2020 && a >= 2008 ) && (b >= 1 && b <=12) && (c >=1 && c <= 31)
  }

  // Use to determine if a repo was created on a certain day
  def createdOnNthDay (year: Int, month:Int,day:Int)(Repo: Repo): Boolean = {
    val compare:String = year + "-" + month + "-" + day
    if (!validDate(year,month,day)) false
    else {
      val date = format.parse(Repo.createdDate)
      val date2 = format.parse(compare)
      date.equals(date2)
    }
  }

  // Use to determine if a repo was created before a certain day
  def createdBeforeDate (year: Int, month:Int,day:Int)(Repo: Repo): Boolean = {
    val compare:String = year + "-" + month + "-" + day
    if (!validDate(year,month,day)) false
    else {
      val date = format.parse(Repo.createdDate)
      val date2 = format.parse(compare)
      date.before(date2)
    }
  }

  // Use to determine if a repo was created after a certain day
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
