package reactive.gwt.demo

import reactive.EventSource


class EventStream_foldLeft extends Demo {
  private val eventSource = new EventSource[String] {}
  
  private case class AvgState(total: Double, count: Int)
  
  private val eventStream =
    eventSource.foldLeft(AvgState(0,0)) {
      case (AvgState(total, count), s) => AvgState(total+s.length, count+1)
    } map {
      case AvgState(total, count) =>
        "Average length so far: " + (total/count)
    }
    
  val widgets = List(Widgets.EventSourceInput(eventSource),
                     Widgets.EventStreamOutput(eventStream))
}
