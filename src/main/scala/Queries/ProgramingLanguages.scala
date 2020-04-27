package Queries

object ProgramingLanguages extends Enumeration {
  type Language = Value
  val All: ProgramingLanguages.Value = Value("All")
  val Java: ProgramingLanguages.Value = Value("Java")
  val Scala: ProgramingLanguages.Value = Value("Scala")
  val CSS: ProgramingLanguages.Value = Value("CSS")
  val HTML: ProgramingLanguages.Value = Value("HTML")
  val JavaScript: ProgramingLanguages.Value = Value("JavaScript")
  val Python: ProgramingLanguages.Value = Value("Python")
  val CPP: ProgramingLanguages.Value = Value("C++")

}
