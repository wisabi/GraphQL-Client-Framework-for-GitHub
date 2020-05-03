package Queries

object ProgramingLanguages extends Enumeration {
  type Language = Value
  val All: ProgramingLanguages.Language = Value("All")
  val Java: ProgramingLanguages.Language = Value("Java")
  val Scala: ProgramingLanguages.Language = Value("Scala")
  val CSS: ProgramingLanguages.Language = Value("CSS")
  val HTML: ProgramingLanguages.Language= Value("HTML")
  val JavaScript: ProgramingLanguages.Language = Value("JavaScript")
  val Python: ProgramingLanguages.Language = Value("Python")
  val CPP: ProgramingLanguages.Language = Value("C++")
}
