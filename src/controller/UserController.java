package controller;

import java.awt.Container;

import models.LogModel;
import view.UserView;

public class UserController implements Controller,UserView.Controller {

	UserView view;
	LogModel model;


	public UserController(UserView view, LogModel model) {
		this.view = view;
		this.model = model;
	}


	public void updateChart(String userIP) {

	}


	@Override
	public void go(Container container) {
		container.add(view.asPanel());
	}

}
