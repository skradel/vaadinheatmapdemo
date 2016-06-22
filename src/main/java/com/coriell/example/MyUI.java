package com.coriell.example;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("com.coriell.example.MyAppWidgetset")
public class MyUI extends UI {

	private static final long serialVersionUID = -6273525953131933758L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final ExampleHeatmap heat = new ExampleHeatmap();

        Button button = new Button("Toggle legend", click -> {
        	Boolean state = heat.getConfiguration().getLegend().getEnabled();
        	heat.getConfiguration().getLegend().setEnabled(state == null ? false : !state.booleanValue());
        	heat.drawChart();
        });
        
        Button tick = new Button("Toggle colorAxis.startOnTick", click -> {
        	Boolean state = heat.getConfiguration().getColorAxis().getStartOnTick();
        	boolean newstate = state == null ? false : !state.booleanValue();
        	heat.getConfiguration().getColorAxis().setStartOnTick(newstate);
        	heat.getConfiguration().getColorAxis().setEndOnTick(newstate);
        	heat.drawChart();
        });

        layout.addComponents(heat, button, tick);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		private static final long serialVersionUID = -6511546163967228118L;
    }
}
