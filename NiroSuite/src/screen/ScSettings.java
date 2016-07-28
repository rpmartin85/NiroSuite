package screen;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScSettings {

	public static JPanel setSettings(JPanel jPSettings, JFrame frame, Rectangle header) {

		

		
		jPSettings.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(jPSettings);
		jPSettings.setLayout(null);

		jPSettings.setBounds(15, header.height + 15, frame.getWidth() - 30, frame.getHeight() - 30 - header.height);

	

		return jPSettings;

	}

	

}
