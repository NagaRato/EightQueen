package model;

import java.util.ArrayList;

import view.Viewer;

public class Model {
	protected ArrayList<Viewer> viewers;
		
	public Model() {
		super();
		this.viewers = new ArrayList<Viewer>();
	}

	public ArrayList<Viewer> getViewers() {
		return viewers;
	}

	public void NotifyViewers() {
		for (Viewer viewer : viewers) {
			viewer.refresh();
		}
	}
}
