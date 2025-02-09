package json

import scala.io.Source
import java.io.File
import parsley.Success
import parsley.Failure

def main(args: Array[String]): Unit = {
    args.headOption match {
        case Some(filename) => {
            val file = File(filename)
            if (!file.exists()) {
                println("File does not exist!")
                sys.exit(-1)
            }
            val prog = Source.fromFile(file).getLines().mkString("\n")
            parser.parse(prog) match {
                case Success(x) => println(x)
                case Failure(msg) => print(msg)
            }
        }
        case None => println("Please provide a json file.")
    }

}