package bpm.training.Setup.client.page;

import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialModal.TYPE;
import gwt.material.design.client.ui.MaterialNavBrand;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.animate.MaterialAnimator;
import gwt.material.design.client.ui.animate.Transition;

import java.util.List;

import bpm.training.Setup.client.ICategoryService;
import bpm.training.Setup.client.card.CategoryCard;
import bpm.training.Setup.client.dialog.AddCategoryDialog;
import bpm.training.Setup.client.panel.LeaderboardPanel;
import bpm.training.Setup.client.panel.ProfilePanel;
import bpm.training.Setup.client.panel.UsersPanel;
import bpm.training.Setup.shared.Category;
import bpm.training.Setup.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class GamePage extends Composite {

	private static GamePageUiBinder uiBinder = GWT
			.create(GamePageUiBinder.class);

	interface GamePageUiBinder extends UiBinder<Widget, GamePage> {
	}
	
	@UiField MaterialContainer conMain;
	@UiField MaterialLink linkLeaderboard, linkProfile, linkQuiz, linkSettings;
	@UiField MaterialRow rowCards;
	@UiField MaterialNavBrand nbTitle;
	
	User user = new User();
	Category cat = new Category();
	
	public GamePage(User user) {
		initWidget(uiBinder.createAndBindUi(this));
		this.user = user;
		nbTitle.setText("Welcome " + user.getFirstName() + " " + user.getLastName().charAt(0) + ".");
		generateCategory();
	}
	
	private void generateCategory(){
		
		MaterialLoader.showLoading(true);
		ICategoryService.Connect.getService().getCategory(new AsyncCallback<List<Category>>() {
			
			@Override
			public void onSuccess(List<Category> result) {
				MaterialLoader.showLoading(false);
				for(Category cat : result){
					rowCards.add(new CategoryCard(cat));
				}
				MaterialAnimator.animate(Transition.SHOW_GRID, rowCards, 0);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MaterialLoader.showLoading(false);
				MaterialToast.alert(caught.getMessage());
			}
			
		});
		
		
	}
	
	@UiHandler({"linkLeaderboard","linkProfile","linkQuiz", "linkSettings"})
	void onClickLeaderboard(ClickEvent e){
		
		if(e.getSource() == linkLeaderboard){
			conMain.clear();
			conMain.add(new LeaderboardPanel());
		}else if(e.getSource() == linkProfile){
			conMain.clear();
			conMain.add(new ProfilePanel(user));
		}else if(e.getSource() == linkQuiz){
			GamePage.this.removeFromParent();
			RootPanel.get().add(new GamePage(user));
		}else if(e.getSource() == linkSettings){
			conMain.clear();
			conMain.add(new UsersPanel());
		}
		
	}
	
	@UiHandler("btnLogOut")
	void onClickLogOut(ClickEvent e){
		
		RootPanel.get().clear();
		RootPanel.get().add(new LoginPage());
	}
	
	@UiHandler("btnAddCategory")
	void onClickAddCategory(ClickEvent e){
		MaterialModal.showModal(new AddCategoryDialog(), TYPE.FIXED_FOOTER, false);
	}
	
}
