package bpm.training.Setup.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface IProjectResource extends ClientBundle {
	public static final IProjectResource INSTANCE = GWT.create(IProjectResource.class);
	
	ImageResource img_google_logo();
}
