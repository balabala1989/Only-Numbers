package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.RadioButtonField;
import net.rim.device.api.ui.component.RadioButtonGroup;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.util.StringProvider;

public class OnlyNumbersScreen extends MainScreen {
    ButtonField submitButton;
    ButtonField helpButton;
    private Bitmap backgroundBitmap;
    private Bitmap displayImage;
    BitmapField fieldBitmap;
    RichTextField rtfHeading;
    Font fontHeading = null;
    RadioButtonGroup rbg;
    RadioButtonField yesField;
    RadioButtonField noField;
    VerticalFieldManager verticalManager;
    
    private static int count = 0;
    private final static int maxCount = 5;
    private static int numberTot = 0;
    
    StringBuffer stHelpContents = new StringBuffer();
    
    public OnlyNumbersScreen() {
    	super(MainScreen.NO_HORIZONTAL_SCROLL | MainScreen.USE_ALL_WIDTH | MainScreen.USE_ALL_HEIGHT | MainScreen.NO_VERTICAL_SCROLL);
        setTitle( "Only Numbers !!!!" );

        backgroundBitmap = Bitmap.getBitmapResource("background.jpg");
        verticalManager  = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH | VerticalFieldManager.USE_ALL_HEIGHT){
        	 public void paint(Graphics graphics)
             {
                 //Draw the background image and then call paint.
                 graphics.drawBitmap(0, 0, Display.getWidth(),Display.getHeight(), backgroundBitmap, 0, 0);
                 super.paint(graphics);
             }
        };
        
       
        
       
        
        // Heading saying Ceiling Floor
        rtfHeading = new RichTextField("Only Numbers !!!!", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(50, 50, 40, 50);
        fontHeading = getFontToDisplay("Times New Roman", 35);
        rtfHeading.setFont(fontHeading);
        verticalManager.add(rtfHeading);
        
        displayImage = Bitmap.getBitmapResource(count + ".jpg");
        fieldBitmap = new BitmapField(displayImage,BitmapField.FIELD_HCENTER); 
        fieldBitmap.setMargin(50,50,40,50);
        
        verticalManager.add(fieldBitmap);
        
      // HorizontalFieldManager horizontalManager = new HorizontalFieldManager(USE_ALL_WIDTH);
        rbg = new RadioButtonGroup();
       yesField = new RadioButtonField("YES", rbg, true, RadioButtonField.NO_USE_ALL_WIDTH);
       yesField.setMargin(5, 5, 5, Display.getWidth()/4);
       noField = new RadioButtonField("NO",rbg,false,RadioButtonField.NO_USE_ALL_WIDTH);
       noField.setMargin(5, 5, 5, Display.getWidth()/4);
         
       verticalManager.add(yesField);
       verticalManager.add(noField);
        
      // verticalManager.add(horizontalManager);
      
		stHelpContents.append("1.  Think of a number between 1 and 63.\n2.  A Picture will be displayed having set of numbers between 1 and 63.\n");
		stHelpContents.append("3.  Select \"YES\" if the number in your mind exists in the picture otherwise \"NO\" and press \"SUBMIT\".\n4.  A series of pictures will be followed and select \"YES\" or \"NO\" and \"SUBMIT\" it.\n5.  App will find and tell the number in your mind");
		fontHeading = getFontToDisplay("Comic Sans MS", 30);
       HorizontalFieldManager horizontal = new HorizontalFieldManager(USE_ALL_WIDTH);
       
       helpButton = new ButtonField("Help", ButtonField.CONSUME_CLICK);
       helpButton.setChangeListener(new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			
			Dialog.inform(stHelpContents.toString());
		}
	});
       helpButton.setFont(fontHeading);
       helpButton.setMargin(35, 10, 10, 80);
       
        submitButton = new ButtonField( "Submit", ButtonField.CONSUME_CLICK );
        // submitButton.setMargin(5, 5, 5, Display.getWidth()/3);
       
        submitButton.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
               if(count < maxCount)
               {
            	   if(yesField.isSelected())
            	   {
            		   numberTot = numberTot + ipow(2,count);
            		   
            	   }
            	   count++;
               }
               else if(count == maxCount)
               {
            	   if(yesField.isSelected())
            		   numberTot = numberTot + ipow(2,count);
            	
            	   Dialog.inform("Number in your mind :\n" + numberTot);
            	   count = 0;
            	   numberTot = 0;
               }
               
               
               try {
            	   
				displayImage = Bitmap.getBitmapResource(count + ".jpg");
				fieldBitmap.setBitmap(displayImage);
			} catch (Exception e) {
				Dialog.inform("Error!!!! Please Try after some time");
			} 
               
               
            }
        } );
        
        submitButton.setFont(fontHeading);
        submitButton.setMargin(35, 10, 10, 80);
        
        horizontal.add(submitButton);
        horizontal.add(helpButton);
        verticalManager.add(horizontal);
        
        add(verticalManager);
    }

    protected void makeMenu( Menu menu, int instance ) {
    	super.makeMenu(menu, instance);
        MenuItem mntmSayHello = new NewMenuItem();
        menu.add( mntmSayHello );
    }

    private class NewMenuItem extends MenuItem {
        public NewMenuItem() {
            super( new StringProvider( "Submit" ), 0, 0 );
        }

        public void run() {
            sayHello();
        }
    }

    private void sayHello() {
    }
    
    
    private Font getFontToDisplay(String stFontName, int fontSize)
    {
    	try
        {
            FontFamily ff1 = FontFamily.forName(stFontName);
            fontHeading = ff1.getFont(Font.ITALIC | Font.EXTRA_BOLD , fontSize);
            return fontHeading;
        }
        catch (Exception e) {
			e.printStackTrace();
			Dialog.inform("Error Occurred. Please try after some time");
			return null;
		}
    }
    
    protected boolean onSavePrompt() {
        return true;
    }
    
    private static int ipow(int base, int exp)
	{
	    int result = 1;
	    while (exp != 0)
	    {
	        if ((exp & 1) == 1)
	            result *= base;
	        exp >>= 1;
	        base *= base;
	    }

	    return result;
	}
}
