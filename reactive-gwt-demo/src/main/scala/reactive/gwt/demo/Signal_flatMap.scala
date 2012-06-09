package reactive.gwt.demo

import reactive.Var


class Signal_flatMap extends Demo {
  private val var1 = Var("This is a Var")
  private val var2 = Var("This is also a Var")
  
  /*
   * equivalent to:
   * var1.flatMap( v1 =>
   *   var2.map( v2 =>
   *     "var1: " + v1 + "var2: " + v2
   *   )
   * )
   */
  private val flatMapped = for {
    v1 <- var1
    v2 <- var2
  } yield "var1: '" + v1 + "'; var2: '" + v2 + "'"
  
  val widgets = List(Widgets.VarInput(var1),
                     Widgets.VarInput(var2),
                     Widgets.SignalOutput(flatMapped))
}
