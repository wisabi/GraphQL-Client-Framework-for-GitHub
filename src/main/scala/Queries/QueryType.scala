package Queries

object QueryType extends Enumeration {
  type Query = Value
  val AllRepos, UserInfo = Value("info")
}
