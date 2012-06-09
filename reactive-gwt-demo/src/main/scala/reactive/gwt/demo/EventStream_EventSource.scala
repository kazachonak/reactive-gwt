package reactive.gwt.demo

import com.google.gwt.user.client.Timer

import reactive.EventSource
import reactive.EventStream


class EventStream_EventSource extends Demo {
  private def scheduleTask(delay: Int)(task: =>Unit) {
    val timer = new Timer { 
      def run {
        task
      }
    }
    timer.schedule(delay)
  }
  
  private val eventSource = {
    val es = new EventSource[String] {}

    scheduleTask(10000) {
      es.fire("Event after 10 seconds")
      
      for(i <- 1 to 3) {
        scheduleTask(i*5000) {
          es.fire("Event after 5 seconds #" + i.toString) 
        }
      }
    }

    es
  }
  
  private val eventStream: EventStream[String] = eventSource
  
  val widgets = List(Widgets.EventSourceInput(eventSource),
                     Widgets.EventStreamOutput(eventStream))
}
