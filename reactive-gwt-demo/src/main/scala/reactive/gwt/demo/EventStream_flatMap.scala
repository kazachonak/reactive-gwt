package reactive.gwt.demo

import reactive.EventSource
import reactive.Timer


//lazy, singleton; never stops, and shared by all
object timer extends Timer(System.currentTimeMillis, 2000)


class EventStream_flatMap extends Demo {
  private val eventSource = new EventSource[String] {}
                     
  private val seconds = timer.map(_ / 1000 % 100000)
  // New EventStream based on input event
  // enter > 5 chars to count down
  private val eventStream =
    eventSource.flatMap( s =>
      if(s.length > 5) {
        val current = System.currentTimeMillis % 100000
        seconds.map(tick => s + " at " + (current-tick))
      } else
        seconds.map(s + " at " + _)
    )
  
  // not actually being used
  private def equivalentWithSugar = for {
    s <- eventSource
    t <- seconds
  } yield s + " at " + t

  val widgets = List(Widgets.EventSourceInput(eventSource),
                     Widgets.EventStreamOutput(eventStream))
}
