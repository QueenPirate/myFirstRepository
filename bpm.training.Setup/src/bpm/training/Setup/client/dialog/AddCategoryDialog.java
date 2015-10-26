package bpm.training.Setup.client.dialog;

import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import bpm.training.Setup.client.ICategoryService;
import bpm.training.Setup.shared.Category;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AddCategoryDialog extends Composite {

	private static AddCategoryDialogUiBinder uiBinder = GWT
			.create(AddCategoryDialogUiBinder.class);

	interface AddCategoryDialogUiBinder extends
			UiBinder<Widget, AddCategoryDialog> {
	}

	@UiField MaterialTextBox lblCategoryName, lblUrlPic;
	@UiField MaterialTextArea lblCatDesc;
	@UiField MaterialImage imgCategory;
	@UiField MaterialListBox lbColor;
	
	
	public AddCategoryDialog() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("btnShowUrlPic")
	void onClickShowUrlPic(ClickEvent e){
		
		imgCategory.setUrl(lblUrlPic.getText());
		MaterialToast.alert(lbColor.getSelectedItemText() + " " + lbColor.getSelectedIndex());
	}
	
	@UiHandler("btnAdd")
	void onClickAddCategory(ClickEvent e){
		
		if(lblCategoryName.getText().isEmpty()){
			lblCategoryName.setError("Provide category name");
		}else{
			lblCategoryName.setSuccess(" ");
			if(lblUrlPic.getText().isEmpty()){
				lblUrlPic.setError("Provide pic of category");
			}else{
				lblUrlPic.setSuccess(" ");
			}
		}
		if(lblUrlPic.getText().isEmpty()){
			lblUrlPic.setError("Provide pic of category");
		}else{
			lblUrlPic.setSuccess(" ");
			if(lblCategoryName.getText().isEmpty()){
				lblCategoryName.setError("Provide category name");
			}else{
				lblCategoryName.setSuccess(" ");
				Category category = new Category(lblUrlPic.getText(), lblCategoryName.getText(), lbColor.getSelectedItemText(), "white");
				addCategory(category);
			}
		}
	
	}
	
	
	@UiHandler("btnClose")
	void onClickClose(ClickEvent e){
		MaterialModal.closeModal();
	}
	
	private void addCategory(Category category){
		
		MaterialLoader.showLoading(true);
		ICategoryService.Connect.getService().addCategory(category, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				MaterialLoader.showLoading(false);
				MaterialToast.alert("Added Category");
				MaterialModal.closeModal();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MaterialLoader.showLoading(false);
				MaterialToast.alert(caught.getMessage());
			}
		});
	}
	
}
