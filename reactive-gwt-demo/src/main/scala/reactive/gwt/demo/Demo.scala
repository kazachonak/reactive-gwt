package reactive.gwt.demo

import com.google.gwt.user.client.ui.Widget
import reactive.Observing


trait Demo extends Observing {
  val widgets: List[Widget]
}
