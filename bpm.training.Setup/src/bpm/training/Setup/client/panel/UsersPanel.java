package bpm.training.Setup.client.panel;

import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialToast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import bpm.training.Setup.client.IUserService;
import bpm.training.Setup.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;

public class UsersPanel extends Composite {

	private static UsersPanelUiBinder uiBinder = GWT
			.create(UsersPanelUiBinder.class);

	interface UsersPanelUiBinder extends UiBinder<Widget, UsersPanel> {
	}

	private DataGrid<User> dataGrid;
	private ListDataProvider<User> containerProvider;
	private ListHandler<User> sortDataHandler;
	List<User> users = new ArrayList<User>();
	
	@UiField SimplePanel gridPanel, pagerPanel;
	
	public UsersPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		setGrid();
	}

	private void setGrid() {
		dataGrid = createGrid();
		gridPanel.setWidget(dataGrid);
		refreshData();
	}

	private void refreshData() {
		containerProvider.setList(new ArrayList<User>());
		getAllContainer();
	}

	private void getAllContainer() {
		MaterialLoader.showLoading(true);
		IUserService.Connect.getService().getAllUsers(new AsyncCallback<List<User>>() {
			
			@Override
			public void onSuccess(List<User> result) {
				MaterialLoader.showLoading(false);
				UsersPanel.this.users = result;
				populateTable(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MaterialLoader.showLoading(false);
				MaterialToast.alert(caught.getMessage());
			}
		});
		
	}

	private void populateTable(List<User> result) {
		
		containerProvider.setList(result);
		sortDataHandler.setList(containerProvider.getList());
		
	}
	
	private DataGrid<User> createGrid() {
		this.sortDataHandler = new ListHandler<User>(new ArrayList<User>());
		TextColumn<User> idCol = new TextColumn<User>() {

			@Override
			public String getValue(User object) {	
				return String.valueOf(object.getUserId());
			}
			
		};
		idCol.setSortable(true);
		sortDataHandler.setComparator(idCol, new Comparator<User>() {
			
			@Override
			public int compare(User o1, User o2) {
				return Long.compare(o1.getUserId(), o2.getUserId()) ;
			}
		});
		TextColumn<User> firstNameCol = new TextColumn<User>() {

			@Override
			public String getValue(User object) {
				return object.getFirstName();
			}
			
		};
		firstNameCol.setSortable(true);
		TextColumn<User> lastNameCol = new TextColumn<User>() {

			@Override
			public String getValue(User object) {
				return object.getLastName();
			}
			
		};
		lastNameCol.setSortable(true);
		TextColumn<User> emailCol = new TextColumn<User>() {

			@Override
			public String getValue(User object) {
				return object.getEmailAdd();
			}
		};
		sortDataHandler.setComparator(lastNameCol, new Comparator<User>() {
			
			@Override
			public int compare(User o1, User o2) {
				return o1.getLastName().compareTo(o2.getLastName());
			}
		});
		
		
		final ProvidesKey<User> KEY_PROVIDER = new ProvidesKey<User>(){

			@Override
			public Object getKey(User item) {
				return item.getUserId();
			}
			
		};
		
		final DataGrid<User> dataGrid = new DataGrid<User>(10, KEY_PROVIDER);
		dataGrid.setSize("100%", "75vh");
		dataGrid.addColumn(idCol, "User ID");
		dataGrid.addColumn(firstNameCol, "First Name");
		dataGrid.addColumn(lastNameCol, "Last Name");
		dataGrid.addColumn(emailCol, "Email Address");
		
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(dataGrid);
		pagerPanel.add(pager);
		
		containerProvider = new ListDataProvider<User>();
		containerProvider.addDataDisplay(dataGrid);
		dataGrid.addColumnSortHandler(sortDataHandler);
		
		
		
		return dataGrid;
	}

}
