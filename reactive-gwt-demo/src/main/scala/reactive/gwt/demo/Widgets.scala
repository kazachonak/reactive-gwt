package reactive.gwt.demo

import com.google.gwt.event.dom.client.ClickEvent
import com.google.gwt.event.dom.client.ClickHandler
import com.google.gwt.event.dom.client.KeyUpEvent
import com.google.gwt.event.dom.client.KeyUpHandler
import com.google.gwt.user.client.DOM
import com.google.gwt.user.client.ui.Button
import com.google.gwt.user.client.ui.FlowPanel
import com.google.gwt.user.client.ui.HTML
import com.google.gwt.user.client.ui.Label
import com.google.gwt.user.client.ui.Panel
import com.google.gwt.user.client.ui.TextBox

import reactive.EventSource
import reactive.EventStream
import reactive.Observing
import reactive.Signal
import reactive.Var


object Widgets {

  def SignalOutput(signal: Signal[String])(implicit o: Observing): Label = {
    val label = new Label
    signal.foreach(label.setText)
    label
  }
  
  def VarInput(v: Var[String])(implicit o: Observing): TextBox = {
    val textBox = new TextBox
    textBox.setText(v.now)
    textBox.addKeyUpHandler(new KeyUpHandler {
      def onKeyUp(event: KeyUpEvent) {
        v.update(textBox.getText)
      }
    })
    textBox
  }

  def EventStreamOutput(eventStream: EventStream[String])(implicit o: Observing): HTML = {
    val html = new HTML
    eventStream.foreach{ event =>
      val div = DOM.createDiv
      div.setInnerText(event)
      html.getElement.appendChild(div)
    }
    html
  }
  
  def EventSourceInput(eventSource: EventSource[String])(implicit o: Observing): Panel = {
    val panel = new FlowPanel
    val textBox = new TextBox
    val button = new Button("Fire")
    button.addClickHandler(new ClickHandler {
      def onClick(event: ClickEvent) = {
        eventSource.fire(textBox.getText)
      }
    })
    panel.add(textBox)
    panel.add(button)
    panel
  }

}