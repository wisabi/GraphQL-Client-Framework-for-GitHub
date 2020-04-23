package RepoParser
import org.json4s._
import org.json4s.jackson.JsonMethods._
class tst extends App {



  implicit val formats = DefaultFormats

  case class Person(name:String, age: Int)

  val jsValue = parse("""{"name":"john", "age": 28}""")

  val p = jsValue.extract[Person]
  // p is Person("john",28)

  val maybeP = jsValue.extractOpt[Person]
  print(maybeP)
}
