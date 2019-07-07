package mypackage;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;

/**
 * This class extends the UiApplication class, providing a graphical user interface.
 */
public class OnlyNumbers extends UiApplication {
    /**
     * Entry point for application
     * 
     * @param args
     *            Command line arguments (not used)
     */
    public static void main( String[] args ) {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        OnlyNumbers theApp = new OnlyNumbers();
        theApp.enterEventDispatcher();
    }

    /**
     * Creates a new OnlyNumbers object
     */
    public OnlyNumbers() {
        // Push a screen onto the UI stack for rendering.
    	getDisplayDirection();
        pushScreen( new OnlyNumbersScreen() );
    }
    
    
public static int getDisplayDirection() {
		
		UiEngineInstance engine = Ui.getUiEngineInstance();
		engine.setAcceptableDirections(Display.DIRECTION_NORTH);
		return Display.DIRECTION_PORTRAIT|Display.DIRECTION_WEST;
		
	}
}
