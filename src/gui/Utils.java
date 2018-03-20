package gui;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;

public class Utils {

    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    /*
     * Get the extension of a file.
     */  
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    public static ImageIcon createIcon(String path) {
		URL url = System.class.getResource(path);
		if(url==null) {
			System.err.println("Unable to load image: "+path);
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
}