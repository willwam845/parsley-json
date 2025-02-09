package json

import parsley.quick.*
import parsley.Result
import lexer.implicits.*
import lexer.{fully, string, float, integer}

object parser {
    def parse(input: String): Result[String, Value] = parser.parse(input)
    private val parser = fully(value)
    private lazy val obj: Parsley[Value] = "{" ~> Object(members) <~ "}"
    private lazy val value = obj 
                           | arrayValue 
                           | stringValue
                           | boolValue 
                           | nullValue 
                           | atomic(floatValue) 
                           | integerValue

    private lazy val members = sepBy(member, ",")
    private lazy val member = Member(string <~ ":", value)
    private lazy val arrayValue: Parsley[Value] = "[" ~> ArrayValue(sepBy(value, ",")) <~ "]"
    private lazy val stringValue = StringValue(string)
    private lazy val boolValue = BoolValue(("true" as true) | ("false" as false))
    private lazy val nullValue = "null" as NullValue
    private lazy val floatValue = FloatValue(float)
    private lazy val integerValue = IntegerValue(integer)
}