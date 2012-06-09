package reactive.gwt.demo

import reactive.EventSource


class EventStream_filter extends Demo {
  private val eventSource = new EventSource[String] {}
  // Only allow short events
  private val eventStream = eventSource.filter(_.length < 5)

  val widgets = List(Widgets.EventSourceInput(eventSource),
                     Widgets.EventStreamOutput(eventStream))
}
