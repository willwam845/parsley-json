package json

import parsley.Parsley
import parsley.token.Lexer
import parsley.token.descriptions.*
import parsley.token.Unicode

object lexer {
    private val whitespace: Set[Int] = Set('\u0020', '\u000a', '\u000d', '\u0009')

    private val desc = LexicalDesc.plain.copy(
        spaceDesc = SpaceDesc.plain.copy(
            space = Unicode(whitespace.contains(_))
        ),
        textDesc = TextDesc.plain.copy(
            characterLiteralEnd = '\'',
            stringEnds = Set(("\"", "\"")),
            escapeSequences = EscapeDesc.plain.copy(
                escBegin = '\\',
                literals = Set('\"', '\\', '/'),
                mapping = Map(
                    "b" -> '\b',
                    "f" -> '\f',
                    "n" -> '\n',
                    "r" -> '\r',
                    "t" -> '\t'
                ) // TODO: handle unicode escapes of the form \uXXXX
            )
        ),
        numericDesc = NumericDesc.plain.copy(
            leadingZerosAllowed = false,
            leadingDotAllowed = false,
            trailingDotAllowed = false,
            integerNumbersCanBeBinary = false,
            integerNumbersCanBeOctal = false,
            integerNumbersCanBeHexadecimal = false
        )
    )
    val lexer = Lexer(desc)
    val implicits = lexer.lexeme.symbol.implicits
    val string = lexer.lexeme.string.fullUtf16
    val integer = lexer.lexeme.integer.decimal
    val float = lexer.lexeme.floating.number
    def fully[A](p: Parsley[A]): Parsley[A] = lexer.fully(p)
}