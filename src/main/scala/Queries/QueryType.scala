package Queries

object QueryType extends Enumeration {
  type Query = Value
  val RepoInfo: String =  "    Connection: edges {  " +
                          "      Repo: node { " +
                          "                   Owner_and_Repo: nameWithOwner " +
                          "                   Created: createdAt " +
                          "                   Last_Pushed: pushedAt " +
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


  val MyRepos: QueryType.Query = Value( "{ \"query\": \"query{ viewer{ RepositoriesConnections: repositories{ " + RepoInfo + " } } } \" }")
  val MyContributedToRepos: QueryType.Query = Value( "query{ viewer{ RepositoriesConnections: repositoriesContributedTo{ " + RepoInfo + " } }")

/*
"{  \"query\":      \"" + client_data.repos + "\", " +
    "   \"operationName\": \"ObtainRepos\",  " +
    "   \"variables\":  { \"allRepos\": true } " +
    "}"
*/
  val UserInfo: QueryType.Query = Value("info")

  val AllRepos: QueryType.Query = Value(    "query ObtainRepos($allRepos: Boolean!){ " +
    " viewer {" +
    "    ...AllRepos @include(if: $allRepos) "+
    "  } " +
    "}" +
    " fragment AllRepos on User {  " +
    "    Users_Own_Repos: repositories {  "+
    "      ...repoInfo  " +
    "    }  " +
    " }  "+
    " fragment repoInfo on RepositoryConnection {  " +
    "    ListOfRepos: edges {  " +
    "      Repo: node { " +
    "                   Owner_and_Repo: nameWithOwner " +
    "                   Created: createdAt " +
    "                   Last_Pushed: pushedAt " +
    "                   Description: description " +
    "                   Disk_Usage : diskUsage " +
    "                   forks {  " +
    "                     totalCount " +
    "                   }  " +
    "                   pullRequests {  " +
    "                     totalCount " +
    "                   }  "+
    "                   LanguagesUsed: languages(first: 3){ "+
    "                     ListOfLanguages: edges { "+
    "                       ProgramingLanguage: node { "+
    "                         Language: name "+
    "                       } " +
    "                     } " +
    "                   } " +
    "                } " +
    "   } " +
    " } ")
}
