package reactive.gwt.demo

import reactive.Var


class Signal_map extends Demo {
  private val myVar = Var("This is a Var")
  
  private val mapped = myVar.map(s => "Reversed: "+s.reverse)
  
  val widgets = List(Widgets.VarInput(myVar),
                     Widgets.SignalOutput(mapped))
}
