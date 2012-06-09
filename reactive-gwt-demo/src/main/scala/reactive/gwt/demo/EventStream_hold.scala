package reactive.gwt.demo

import reactive.EventSource


class EventStream_hold extends Demo {
  private val eventSource = new EventSource[String] {}
  private val signal = eventSource.hold("(initial value of signal)")
  
  val widgets = List(Widgets.EventSourceInput(eventSource),
                     Widgets.SignalOutput(signal.map{ value =>
                       "Signal value: "+value+"."
                     }))
}
