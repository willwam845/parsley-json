package json

import parsley.quick.*
import parsley.Result
import lexer.implicits.*
import lexer.{fully}

object parser {
    def parse(input: String): Result[String, Value] = parser.parse(input)
    private val parser = fully(obj)
    private lazy val obj: Parsley[Value] = Object(pure(List.empty))
}