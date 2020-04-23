package RepoParser

class xx {
//  case class Forks(totalCount: Double)
case class Forks(
                  totalCount: Double
                )
  case class ProgramingLanguage(
                                 Language: String
                               )
  case class ListOfLanguages(
                              ProgramingLanguage: ProgramingLanguage
                            )
  case class LanguagesUsed(
                            ListOfLanguages: List[ListOfLanguages]
                          )
  case class Repo(
                   Owner_and_Repo: String,
                   Created: String,
                   Last_Pushed: String,
                   Description: String,
                   Disk_Usage: Double,
                   forks: Forks,
                   pullRequests: Forks,
                   LanguagesUsed: LanguagesUsed
                 )
  case class ListOfRepos(
                          Repo: Repo
                        )
  case class Users_Own_Repos(
                              ListOfRepos: List[ListOfRepos]
                            )
  case class Viewer(
                     Users_Own_Repos: Users_Own_Repos,
                     Contributed_To_Repos: Users_Own_Repos
                   )
  case class Data(
                   viewer: Viewer
                 )
  case class R00tJsonObject(
                             data: Data
                           )



}



