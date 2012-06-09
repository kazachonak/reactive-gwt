package reactive.gwt.demo

import com.google.gwt.user.client.Window

import reactive.EventSource


class EventStream_foreach extends Demo {
  private val eventSource = new EventSource[String] {}
  
  //The following is syntactic sugar for
  // eventSource.foreach(event => alert("You fired: '" + event + "'"))
  for(event <- eventSource) {
    Window.alert("You fired: '" + event + "'")
  }
  
  val widgets = List(Widgets.EventSourceInput(eventSource))
}
