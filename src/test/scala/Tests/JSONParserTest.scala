package Tests

import RepoParser.JSONParser
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.FunSuite

class JSONParserTest extends FunSuite {

    val UserOwnJSON: Config = ConfigFactory.load("resources.conf")
    val UserOwnJSONString: String = UserOwnJSON.getString("UserOwnJSON.JSONString")
    val ContributedToJSON: Config = ConfigFactory.load("resources.conf")
    val ContributedToString: String = ContributedToJSON.getString("ContributedTo.JSONString")
    val SpecificUserRepoJSON: Config = ConfigFactory.load("resources.conf")
    val SpecificUserRepoString: String = SpecificUserRepoJSON.getString("SpecificUserRepo.JSONString")


    test("Test Owner login name") {
      val seq = JSONParser.getUserOwnRepo(UserOwnJSONString)
      assert(seq(1).owner.loginName === "jsanchez78")
    }

    test("Test a Owner Repo Name") {
      val seq = JSONParser.getUserOwnRepo(UserOwnJSONString)
      assert(seq(16).repoName === "hello_world")
    }

    test("Test a Owner Repo Language") {
      val seq = JSONParser.getUserOwnRepo(UserOwnJSONString)
      assert(seq(10).primaryLanguage.language === "C++")
    }

    test("Test a Owner Repo Description") {
      val seq = JSONParser.getUserOwnRepo(UserOwnJSONString)
      assert(seq(23).description === "Programming Languages and Environment (U. of Illinois, Chicago)")
    }



    test("Test Contributed to Repo Owner") {
      val seq = JSONParser.getUserOwnRepo(ContributedToString)
      assert(seq(0).owner.loginName === "EmilioAVazquez")
    }

    test("Test Contributed to Repo Name") {
      val seq = JSONParser.getUserOwnRepo(ContributedToString)
      assert(seq(2).repoName === "cs494-s20-a1-jsanchez78")
    }

    test("Test Contributed to Creation Date") {
      val seq = JSONParser.getUserOwnRepo(ContributedToString)
      assert(seq(0).createdDate === "2019-08-20T22:25:14Z")
    }



  test("Test Specific User Owner") {
    val seq = JSONParser.getSpecificUserRepo(SpecificUserRepoString)
    assert(seq(0).owner.loginName === "wisabi")
  }

  test("Test Specific User Repo Name") {
    val seq = JSONParser.getSpecificUserRepo(SpecificUserRepoString)
    assert(seq(14).repoName === "IntelliJDesignPatternPlugin")
  }

  test("Test Specific User Repo Created Date") {
    val seq = JSONParser.getSpecificUserRepo(SpecificUserRepoString)
    assert(seq(14).createdDate === "2020-04-22T20:12:37Z")
  }

  test("Test Specific User Repo Last Pushed Date") {
    val seq = JSONParser.getSpecificUserRepo(SpecificUserRepoString)
    assert(seq(14).lastPushed === "2020-04-23T19:22:57Z")
  }


  test("Test Specific User Repo Primary Language") {
    val seq = JSONParser.getSpecificUserRepo(SpecificUserRepoString)
    assert(seq(14).primaryLanguage.language === "Java")
  }
}

