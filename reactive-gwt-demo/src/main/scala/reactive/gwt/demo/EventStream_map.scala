package reactive.gwt.demo

import reactive.EventSource


class EventStream_map extends Demo {
  private val eventSource = new EventSource[String] {}
  // Reverse the event
  private val eventStream = eventSource.map(_.reverse)

  val widgets = List(Widgets.EventSourceInput(eventSource),
                     Widgets.EventStreamOutput(eventStream))
}
