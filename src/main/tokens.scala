package json

import parsley.generic.*

sealed trait Value

case class Object(members: List[Member]) extends Value
case class ArrayValue(l: List[Value]) extends Value
case class StringValue(v: String) extends Value
case class IntegerValue(v: BigInt) extends Value
case class FloatValue(v: BigDecimal) extends Value
case class BoolValue(v: Boolean) extends Value
case object NullValue extends Value

case class Member(name: String, v: Value)

object Object extends ParserBridge1[List[Member], Value]
object ArrayValue extends ParserBridge1[List[Value], Value]
object StringValue extends ParserBridge1[String, Value]
object IntegerValue extends ParserBridge1[BigInt, Value]
object FloatValue extends ParserBridge1[BigDecimal, Value]
object BoolValue extends ParserBridge1[Boolean, Value]

object Member extends ParserBridge2[String, Value, Member]