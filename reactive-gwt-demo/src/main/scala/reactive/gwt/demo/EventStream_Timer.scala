package reactive.gwt.demo

import reactive.Timer


class EventStream_Timer extends Demo {
  // Create a timer that fires every 2 seconds, starting at 0,
  // for 30 seconds
  private val timer = new Timer(0, 2000, {t =>  t >= 32000})
  
  val widgets = List(
    Widgets.EventStreamOutput(
      for(t <- timer)
        yield "timer: " + t.toString
    )
  )
}
