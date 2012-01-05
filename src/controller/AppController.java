package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.LogModel;
import view.AppLayoutView;
import view.AppLayoutViewImpl;
import view.UserView;
import view.UserViewImpl;

public class AppController implements Controller, AppLayoutView.Controller {

	private final LogModel model;
	private final AppLayoutViewImpl view;
	private int selectedTableRow = 0;

	private File dbFile;
	private final String dbFilePath;
	private BufferedReader br;
	JComponent container;


	UserController userController;

	public AppController(LogModel model, AppLayoutViewImpl view, String dbFilePath) {
		this.model = model;
		this.view = view;
		this.dbFilePath = dbFilePath;

		container = view.asContainer();
		view.setController(this);
		 dbFileExists();
		 readDBFile();
		 model.filterDuplicateIPs();
		view.updateUI();
		bind();
	}

	class LoadLog implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
		}
	}

	class TabelSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (arg0.getValueIsAdjusting()) {
				setSelectedTabelRow(view.getViewTable().getSelectedRow());

			 int i = 0;
			 String userIP = "";
			 for( String key : model.keySet()) {
				 if(i == selectedTableRow) {
					 System.out.println(key);
					 userIP = key;
				 }
				 i++;
			 }
			userController.updateChart(userIP);
			}
		}
	};

	class MenuOeffnen implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Traffic * txt", "txt", "log");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if (chooser.getSelectedFile().isFile()) {
					dbFile = new File(chooser.getSelectedFile().getAbsolutePath());
					if (!dbFile.exists()) {
						System.out.println("File not exist!!");
						System.exit(0);
					}
					readDBFile();
					model.filterDuplicateIPs();
					view.updateUI();
					// view.resetTable();
				}
			}
		}
	}

	public void bind() {
		view.setMenuOeffenenListener(new MenuOeffnen());
		view.setTableSelectionListener(new TabelSelectionListener());
		UserView uv = new UserViewImpl();
		userController = new UserController(uv, model);
		userController.go(container);
		view.pack();
	}

	public void readDBFile() {
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(dbFile)));
			String line = "";
			String date = "";
			String time = "";
			String bytes = "";
			String ip = "";
			String direction = "";
			// read line and add to mp3List
			while ((line = br.readLine()) != null && line.length() > 10) {
				// System.out.println(line);
				String[] temp = line.split(" ");
				ip = temp[3];
				date = temp[0];
				time = temp[1];
				bytes = temp[2];
				direction = temp[4];
				model.addEntryToLogList(ip, date, time, bytes, direction);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Boolean dbFileExists() {
		return (dbFile = new File(dbFilePath)).exists();
	}

	@Override
	public void go(Container container) {
		container.add(container);
	}

	@Override
	public void setSelectedTabelRow(int row) {
		this.selectedTableRow = row;
	}
}
