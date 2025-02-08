package json

import parsley.Parsley
import parsley.token.Lexer
import parsley.token.descriptions.*

object lexer {
    private val desc = LexicalDesc.plain.copy()
    val lexer = Lexer(desc)
    val implicits = lexer.lexeme.symbol.implicits
    val string = lexer.lexeme.string.ascii
    val integer = lexer.lexeme.integer.decimal
    val float = lexer.lexeme.floating.number
    def fully[A](p: Parsley[A]): Parsley[A] = lexer.fully(p)
}