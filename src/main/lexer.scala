package json

import parsley.Parsley
import parsley.token.Lexer
import parsley.token.descriptions.*

object lexer {
    private val desc = LexicalDesc.plain.copy()
    val lexer = Lexer(desc)
    val implicits = lexer.lexeme.symbol.implicits
    def fully[A](p: Parsley[A]): Parsley[A] = lexer.fully(p)
}