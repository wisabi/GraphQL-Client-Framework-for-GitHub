package Queries

/*
  Type: Language
  Use:  To facilitate the filtering/query process for the scala client. Specifically,
        for creating List of languages of which a client would want included during
        a query of collection-filter call. Values are provided in such that they match
        how github represents the language on their end.
 */

object ProgramingLanguages extends Enumeration {
  type Language = Value
  val Java: ProgramingLanguages.Language = Value("Java")
  val Scala: ProgramingLanguages.Language = Value("Scala")
  val CSS: ProgramingLanguages.Language = Value("CSS")
  val HTML: ProgramingLanguages.Language= Value("HTML")
  val JavaScript: ProgramingLanguages.Language = Value("JavaScript")
  val Python: ProgramingLanguages.Language = Value("Python")
  val CPP: ProgramingLanguages.Language = Value("CPP")
}
