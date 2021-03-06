/**
 * [MaterialLookAndFeel.java]
 * General Material styles
 * @author Atharva Washimkar, adapted by Carol
 */

 import javax.swing.BorderFactory;
 import javax.swing.UIDefaults;
 import javax.swing.border.Border;
 import javax.swing.plaf.basic.BasicLookAndFeel;
 import java.awt.Color;

 public class MaterialLookAndFeel extends BasicLookAndFeel {

 	private static final String buttonUI = MaterialButtonUI.class.getCanonicalName ();
 	private static final String textfieldUI = MaterialTextFieldUI.class.getCanonicalName ();

    public static final Color LIGHT_GRAY = new Color (230, 230, 230);
    public static final Color LIGHT_BLUE = new Color (25, 181, 254);

 	@Override

 	public String getName () {
 		return "mdlaf.MaterialLookAndFeel";
 	}

 	@Override
 	public String getID () {
 		return "mdlaf.MaterialLookAndFeel";
 	}

 	@Override
 	public String getDescription () {
 		return "A modern, Material Design UI for Java Swing";
 	}

 	@Override
 	public boolean isNativeLookAndFeel () {
 		return false;
 	}

 	@Override
 	public boolean isSupportedLookAndFeel () {
 		return true;
 	}

 	@Override
 	protected void initClassDefaults (UIDefaults table) {
 		super.initClassDefaults (table);
 		table.put ("ButtonUI", buttonUI);
 		table.put ("TextFieldUI", textfieldUI);
 	}

 	@Override
 	protected void initSystemColorDefaults (UIDefaults table) {
 		super.initSystemColorDefaults (table);
 	}

 	@Override
 	protected void initComponentDefaults (UIDefaults table) {
 		super.initComponentDefaults (table);

 		table.put ("Button.font", Fonts.MEDIUM);
 		table.put ("RadioButton.font", Fonts.LIGHT);
 		table.put ("CheckBox.font", Fonts.LIGHT);
 		table.put ("ComboBox.font", Fonts.LIGHT);
 		table.put ("Label.font", Fonts.LIGHT);
 		table.put ("MenuBar.font", Fonts.BOLD);
 		table.put ("MenuItem.font", Fonts.MEDIUM);
 		table.put ("Menu.font", Fonts.BOLD);
 		table.put ("OptionPane.font", Fonts.LIGHT);
 		table.put ("Panel.font", Fonts.LIGHT);
 		table.put ("ScrollPane.font", Fonts.LIGHT);
 		table.put ("Table.font", Fonts.LIGHT);
 		table.put ("TableHeader.font", Fonts.LIGHT);
 		table.put ("TextField.font", Fonts.MEDIUM);
 		table.put ("TextArea.font", Fonts.MEDIUM);

 		table.put ("Panel.background", Color.WHITE);
 		table.put ("Panel.border", BorderFactory.createEmptyBorder ());

 		table.put ("MenuItem.background", Color.WHITE);
 		table.put ("MenuItem.border", BorderFactory.createEmptyBorder (5, 5, 5, 5));
 		table.put ("MenuItem.disabledForeground", new Color (0, 0, 0, 100));
 		table.put ("MenuItem.selectionBackground", MaterialLookAndFeel.LIGHT_GRAY);
 		table.put ("MenuItem.selectionForeground", Color.BLACK);
 		table.put ("MenuItem.foreground", Color.BLACK);

 		table.put ("PopupMenu.border", BorderFactory.createLineBorder (MaterialLookAndFeel.LIGHT_GRAY, 1));
 		table.put ("PopupMenu.background", Color.WHITE);
 		table.put ("Menu.border", BorderFactory.createEmptyBorder (5, 5, 5, 5));
 		table.put ("Menu.selectionBackground", MaterialLookAndFeel.LIGHT_GRAY);
 		table.put ("Menu.selectionForeground", Color.BLACK);
 		table.put ("Menu.disabledForeground", new Color (0, 0, 0, 100));
 		table.put ("Menu.background", Color.WHITE);
 		table.put ("Menu.foreground", Color.BLACK);
 		table.put ("Menu.opaque", true);
 		table.put ("Menu.menuPopupOffsetY", 10);

 		table.put ("MenuBar.background", Color.WHITE);

 		table.put ("SplitPane.border", BorderFactory.createEmptyBorder ());
 		table.put ("SplitPane.background", Color.WHITE);
 		table.put ("SplitPane.dividerSize", 5);
 		table.put ("SplitPaneDivider.border", BorderFactory.createEmptyBorder ());

 		table.put ("ScrollPane.background", Color.WHITE);
 		table.put ("ScrollPane.border", BorderFactory.createEmptyBorder ());

 		table.put ("TextField.background", MaterialLookAndFeel.LIGHT_BLUE);

 		table.put ("TextArea.background", MaterialLookAndFeel.LIGHT_GRAY);
 		table.put ("TextArea.border", BorderFactory.createEmptyBorder ());
 		table.put ("TextArea.foreground", Color.BLACK);

 		table.put ("OptionPane.background", Color.WHITE);

 		table.put ("Button.background", MaterialLookAndFeel.LIGHT_BLUE);
 		table.put ("Button.foreground", Color.WHITE);
 		table.put ("Button.highlight", MaterialLookAndFeel.LIGHT_GRAY);
 		//table.put ("Button.border", BorderFactory.createEmptyBorder (10, 10, 10, 10));
 	}

	public static Color bleach (Color color, float amount) {
		int red = (int) ((color.getRed () * (1 - amount) / 255 + amount) * 255);
		int green = (int) ((color.getGreen () * (1 - amount) / 255 + amount) * 255);
		int blue = (int) ((color.getBlue () * (1 - amount) / 255 + amount) * 255);
		return new Color (red, green, blue);
    }
 }
