package reactive.gwt.demo

import reactive.EventSource


class EventStream_union extends Demo {
  private val eventSource1 = new EventSource[String] {}
  private val eventSource2 = new EventSource[String] {}
  
  private val eventStream = eventSource1 | eventSource2

  val widgets = List(Widgets.EventSourceInput(eventSource1),
                     Widgets.EventSourceInput(eventSource2),
                     Widgets.EventStreamOutput(eventStream))
}
