package gitHubObject

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}

object client_data {

 def GetAuthCodeFromConfig(): String = {
  val config: Config = ConfigFactory.load("lightbend.conf")
  val key = config.getString("githubAPI.key")
  key
 }

  val repos =  "query ObtainRepos($allRepos: Boolean!){ " +
    " viewer {" +
    "    ...AllRepos @include(if: $allRepos) "+
    "  } " +
    "}" +
    " fragment AllRepos on User {  " +
    "    Users_Own_Repos: repositories {  "+
    "      ...repoInfo  " +
    "    }  " +
    "    Contributed_To_Repos: repositoriesContributedTo{ " +
    "      ...repoInfo  " +
    "    }  "+
    " }  "+
    " fragment repoInfo on RepositoryConnection {  " +
    "    ListOfRepos: edges {  " +
    "      Repo: node { " +
    "        Owner_and_Repo: nameWithOwner " +
    "        Created: createdAt " +
    "        Last_Pushed: pushedAt " +
    "        Description: description " +
    "        Disk_Usage : diskUsage " +
    "        forks {  " +
    "          totalCount " +
    "        }  " +
    "        pullRequests {  " +
    "          totalCount " +
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
