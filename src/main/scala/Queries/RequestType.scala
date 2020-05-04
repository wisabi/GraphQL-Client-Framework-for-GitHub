package Queries

/*
      Type: QueryRequest
       Use: To represent the actual query string being sent to github's API.
    Values:
            • MyRepos: query to get the repositories of the client (As long as the github authorization
              key belongs to the client).
            • MyContributedToRepos: query to get the repositories the client has contributed to.
            • SpecificUser: query to get the repositories of a specific github user (via login)
    Notes:
            • The query for SpecificUser is fully built once the targeted user is known. Which should be know when
              GithubQuery.build is called (file: Queries.QueryInfo)
            • The collaboratorsConnection field was removed from the SpecificUser because most return null. This is because
              The client (authorization key owner) must have push right to access the collaborators of a repository.
 */

object RequestType extends Enumeration {
  type QueryRequest = Value
  val RepoInfo_NoCollaborators: String ="Repo: nodes{" +
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
                                        "   pullRequestsConnection: pullRequests(last: 10){"+
                                        "     totalCount"+
                                        "     pullRequestsList: nodes"+
                                        "     {"+
                                        "       title"+
                                        "       author{ userLogin: login }" +
                                        "       createdDate: createdAt" +
                                        "     }"+
                                        "   }"+
                                        "   commitComments{"+
                                        "     totalCount"+
                                        "   }"+
                                        "}"

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
                          "       author{ userLogin: login }" +
                          "       createdDate: createdAt" +
                          "     }"+
                          "   }"+
                          "   commitComments{"+
                          "     totalCount"+
                          "   }"+
                          "}"

  val MyRepos: RequestType.QueryRequest = Value(               "{ \"query\": \"{ viewer{ RepositoriesConnections: repositories(first: 100){ " + RepoInfo + " } } } \" }")

  val MyContributedToRepos: RequestType.QueryRequest = Value(  "{ \"query\": \"query { viewer{ RepositoriesConnections: repositoriesContributedTo(first: 100){ " + RepoInfo + " } } } \" }")

  /* More is added to SpecificUser.Value
          userLogin + "\"} }"
    Look At File: Queries.QueryInfo --> Look At Function: GithubQuery.build
  */
  val SpecificUser: RequestType.QueryRequest = Value(           "{ \"query\": \"query SpecificUser($user: String!){ user(login: $user){ RepositoriesConnections: repositories(first: 100){" + RepoInfo_NoCollaborators + " } } } \"," +
                                                                "  \"operationName\": \"SpecificUser\", " +
                                                                "  \"variables\": { \"user\": \"" )
}
