package reactive.gwt.demo

import com.google.gwt.core.client.EntryPoint
import com.google.gwt.user.client.ui.RootPanel
import com.google.gwt.user.client.ui.VerticalPanel
import com.google.gwt.user.client.ui.Widget


class DemosEntryPoint extends EntryPoint {
  
  private def verticalPanelWith(widgets: List[Widget]) = {
    val panel = new VerticalPanel
    widgets.foreach(panel.add)
    panel
  }
  
  private def showIfNeeded(placeId: String, demo: =>Demo) = {
    val root = RootPanel.get(placeId)
    if (root != null) {
      root.add(verticalPanelWith(demo.widgets))
    }
  }

  def onModuleLoad {
    showIfNeeded("EventStream_EventSource", new EventStream_EventSource)
    showIfNeeded("EventStream_filter", new EventStream_filter)
    showIfNeeded("EventStream_flatMap", new EventStream_flatMap)
    showIfNeeded("EventStream_foldLeft", new EventStream_foldLeft)
    showIfNeeded("EventStream_foreach", new EventStream_foreach)
    showIfNeeded("EventStream_hold", new EventStream_hold)
    showIfNeeded("EventStream_map", new EventStream_map)
    showIfNeeded("EventStream_takeWhile", new EventStream_takeWhile)
    showIfNeeded("EventStream_Timer", new EventStream_Timer)
    showIfNeeded("EventStream_union", new EventStream_union)
    showIfNeeded("Signal_map", new Signal_map)
    showIfNeeded("Signal_flatMap", new Signal_flatMap)
  }

}
