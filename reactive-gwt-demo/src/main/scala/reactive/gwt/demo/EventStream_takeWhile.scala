package reactive.gwt.demo

import reactive.EventSource


class EventStream_takeWhile extends Demo {
  private val eventSource = new EventSource[String] {}

  private val eventStream = {
    var stop = false
    eventSource.takeWhile{
      case "stop" => false   // Stop immediately, functional
      case "next" if !stop =>  // Not encouraged, stop via side effect
        stop = true
        true
      case _ => !stop
    }
  }

  val widgets = List(Widgets.EventSourceInput(eventSource),
                     Widgets.EventStreamOutput(eventStream))
}
