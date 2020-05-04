package gitHubObject

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger

object client_data {

 def GetAuthCodeFromConfig(): String = {
  //TODO: if bad credentials, assert that config file bad key
  val configLog = Logger(LoggerFactory.getLogger("logger"))
  val config: Config = ConfigFactory.load("lightbend.conf")
  val key = config.getString("githubAPI.key")
  configLog.trace("Sending Github Authorization key from Configuration file")
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
