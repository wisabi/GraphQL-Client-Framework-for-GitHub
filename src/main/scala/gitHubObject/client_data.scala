package gitHubObject

import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}

object client_data {
  val BASE_GHQL_URL = "https://api.github.com/graphql"
  val temp="{viewer {email login url}}"
  val closeable_connection: CloseableHttpClient = HttpClientBuilder.create.build
  val httpUriRequest = new HttpPost(client_data.BASE_GHQL_URL)

 //gitHubObject.setHeader(Some("Accept"),file_format.APPJSON)
  val repos =  "query ObtainRepos($allRepos: Boolean!){ " +
    " viewer {" +
    "    ...AllRepos @include(if: $allRepos) "+
    "  } " +
    "}" +
    " fragment AllRepos on User {  "+
    "    Users_Own_Repos: repositories {  "+
    "      ...repoInfo  "+
    "    }  "+
    "    Contributed_To_Repos: repositoriesContributedTo{ "+
    "      ...repoInfo  "+
    "    }  "+
    " }  "+
    " fragment repoInfo on RepositoryConnection {  " +
    "    ListOfRepos: edges {  " +
    "      Repo: node { "+
    "        Owner_and_Repo: nameWithOwner "+
    "        Created: createdAt "+
    "        Last_Pushed: pushedAt "+
    "        Description: description "+
    "        Disk_Usage : diskUsage "+
    "        forks {  "+
    "          totalCount "+
    "        }  "+
    "        pullRequests {  "+
    "          totalCount "+
    "        }  "+
    "        LanguagesUsed: languages(first: 3){ "+
    "          ListOfLanguages: edges { "+
    "            ProgramingLanguage: node { "+
    "              Language: name "+
    "            } " +
    "          } " +
    "        } " +
    "     } " +
    "   } " +
    " } "

}
