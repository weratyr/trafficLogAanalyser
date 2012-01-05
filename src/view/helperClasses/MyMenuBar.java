package view.helperClasses;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar extends JMenuBar {

	JMenu datei;
	JMenu help;

	JMenuItem open;
	JMenuItem exit;

	JMenuItem faq;
	JMenuItem about;

	public MyMenuBar() {

		datei = new JMenu("Datei");
		help = new JMenu("Hilfe");
		open = new JMenuItem("šffnen");
		exit = new JMenuItem("beenden");
		faq = new JMenuItem("F.A.Q.");
		about = new JMenuItem("†ber");

		add(datei);
		add(help);

		datei.add(open);
		datei.add(exit);
		help.add(faq);
		help.add(about);
	}

	 public void setMenuOeffenen(ActionListener listener) {
		 open.addActionListener(listener);
	 }


	public JMenuBar getMenu() {
		return this;
	}
}
