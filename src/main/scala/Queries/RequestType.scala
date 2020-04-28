package Queries

object RequestType extends Enumeration {
  type QueryRequest = Value
  val RepoInfo: String =  "    Connection: edges {  " +
                          "      Repo: node { " +
                          "                   RepoName: name" +
                          "                   OwnerAndRepo: nameWithOwner " +
                          "                   Created: createdAt " +
                          "                   LastPushed: pushedAt " +
                          "                   Description: description " +
                          "                   Disk_Usage : diskUsage " +
                          "                   forks { " +
                          "                     totalCount " +
                          "                   } " +
                          "                   pullRequests { " +
                          "                     totalCount " +
                          "                   } "+
                          "                   LanguagesUsed: languages(first: 3){ "+
                          "                     ListOfLanguages: edges { "+
                          "                       ProgramingLanguage: node { "+
                          "                         Language: name "+
                          "                       } " +
                          "                     } " +
                          "                   } " +
                          "                } " +
                          "   }"

  val MyRepos: RequestType.QueryRequest = Value(               "{ \"query\": \"query { viewer{ RepositoriesConnections: repositories(first: 100){ " + RepoInfo + " } } } \" }")

  val MyContributedToRepos: RequestType.QueryRequest = Value(  "{ \"query\": \"query { viewer{ RepositoriesConnections: repositoriesContributedTo(first: 100){ " + RepoInfo + " } } } \" }")

  val SpecificUser: RequestType.QueryRequest = Value(          "{ \"query\": \"query SpecificUser($user: String!){ user(login: $user){ RepositoriesConnections: repositories(first: 100){" + RepoInfo + " } } } \"," +
                                                      "  \"operationName\": \"SpecificUser\", " +
                                                      "  \"variables\": { \"user\": \"" ) // + userLogin + "\"} }"


/*
"{  \"query\":      \"" + client_data.repos + "\", " +
    "   \"operationName\": \"ObtainRepos\",  " +
    "   \"variables\":  { \"allRepos\": true } " +
    "}"
*/

}
