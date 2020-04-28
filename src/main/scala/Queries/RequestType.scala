package Queries

object RequestType extends Enumeration {
  type QueryRequest = Value
  val RepoInfo: String =  "Repo: nodes{" +
                          "   repoName: name"+
                          "   nameWithOwner"+
                          "   createdDate: createdAt"+
                          "   lastPushed: pushedAt"+
                          "   description"+
                          "   primaryLanguage{"+
                          "     language: name"+
                          "   }"+
                          "   owner{"+
                          "     loginName: login"+
                          "   }"+
                          "   forks{"+
                          "     totalCount"+
                          "   }"+
                          "   languagesConnection: languages(first: 25){"+
                          "     programingLanguages: nodes{"+
                          "       language: name"+
                          "     }"+
                          "   }"+
                          "   stargazersConnection: stargazers{"+
                          "     totalCount"+
                          "   }"+
                          "   collaboratorsConnection: collaborators{"+
                          "     totalCount"+
                          "     collaborators: nodes{"+
                          "       name"+
                          "       loginName:login"+
                          "     }"+
                          "   }"+
                          "   pullRequestsConnection: pullRequests(last: 10){"+
                          "     totalCount"+
                          "     pullRequestsList: nodes"+
                          "     {"+
                          "       title"+
                          "       author{ userLogin: login }"+
                          "       createdDate: createdAt"+
                          "       filesConnection: files(first:20){"+
                          "         files: nodes{"+
                          "           path"+
                          "         }"+
                          "       }"+
                          "     }"+
                          "   }"+
                          "   commitComments{"+
                          "     totalCount"+
                          "   }"+
                          "}"

  val MyRepos: RequestType.QueryRequest = Value(               "{ \"query\": \"query { viewer{ RepositoriesConnections: repositories(first: 100){ " + RepoInfo + " } } } \" }")

  val MyContributedToRepos: RequestType.QueryRequest = Value(  "{ \"query\": \"query { viewer{ RepositoriesConnections: repositoriesContributedTo(first: 100){ " + RepoInfo + " } } } \" }")

  val SpecificUser: RequestType.QueryRequest = Value(           "{ \"query\": \"query SpecificUser($user: String!){ user(login: $user){ RepositoriesConnections: repositories(first: 100){" + RepoInfo + " } } } \"," +
                                                                "  \"operationName\": \"SpecificUser\", " +
                                                                "  \"variables\": { \"user\": \"" ) // + userLogin + "\"} }"


/*
"{  \"query\":      \"" + client_data.repos + "\", " +
    "   \"operationName\": \"ObtainRepos\",  " +
    "   \"variables\":  { \"allRepos\": true } " +
    "}"
*/

}
