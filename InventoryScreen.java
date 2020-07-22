//package org.leadedpencilinc.TheBestDnDGameYouWillEverPlay;

import java.util.ArrayList;

//import org.leadedpencilinc.TheBestDnDGameYouWillEverPlay.Items.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryScreen extends Application {

	//Launches the program
	public static void main(String[] args) {
		launch();

	}

	//Adds the main global variables
	Pane mainPane = new Pane();
	ArrayList<Item> items = new ArrayList<>();
	@SuppressWarnings("rawtypes")
	TableView table;
	Pane addItem = new Pane();
	Button addButton = new Button("Add");
	int itemSelection;
	Stage addItemStage;
	
	//adds basic item editing fields
	TextField name = new TextField();
	TextArea description = new TextArea();
	TextField quantity = new TextField();
	TextField weight = new TextField();
	TextField value = new TextField();
	
	//adds weapon editing fields
	TextField d4 = new TextField();
	TextField d6 = new TextField();
	TextField d8 = new TextField();
	TextField d10 = new TextField();
	TextField d12 = new TextField();
	CheckBox range = new CheckBox("Ranged");
	TextField normalRange = new TextField();
	TextField maxRange = new TextField();
	ListView<String> properties = new ListView<>();
	TextField propertyEnter = new TextField();
	
	//adds armor editing fields
	TextField strengthRequirement = new TextField();
	TextField armorClass = new TextField();
	//TextField dexMod = new TextField();
	CheckBox hasDexMod = new CheckBox("Has Dex Mod?");
	TextField maxDex = new TextField();
	
	Scene addItemScene;
	
	//main start method
	@Override
	public void start(Stage mainStage) throws Exception {
		Scene mainScene = new Scene(mainPane,375,500);
		mainPane.getChildren().add(getMainScreen());
		mainStage.setTitle("Inventory");
		
		
		mainStage.setScene(mainScene);
		mainStage.show();
	}

	//basic adding of the main pane 
	private Node getMainScreen() {
		HBox h = new HBox();
		
		h.getChildren().addAll(getListBox(), getButtons());
		return h;
	}

	//adds the TableView to the main inventory screen
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Node getListBox() {
		table = new TableView<>();
		
		//setting up the columns and their respected fields to display
		TableColumn<String, Item> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setPrefWidth(100);
		TableColumn<String, Item> weightColumn = new TableColumn<>("Weight");
		weightColumn.setCellValueFactory(new PropertyValueFactory("Weight"));
		weightColumn.setPrefWidth(50);
		TableColumn<String, Item> valueColumn = new TableColumn<>("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory("Value"));
		valueColumn.setPrefWidth(50);
		TableColumn<String, Item> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory("Quantity"));
		quantityColumn.setPrefWidth(50);
		
		table.getColumns().add(nameColumn);
		table.getColumns().add(weightColumn);
		table.getColumns().add(valueColumn);
		table.getColumns().add(quantityColumn);
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPlaceholder(new Label("No items to display"));
		
		//adding the items from the ArrayList to the display table
		for(int i=0;i<items.size();i++) {
			table.getItems().add(items.get(i));
		}
		
		table.setMinHeight(500);
		table.setMaxWidth(275);
		
		return table;
	}

	//adds the buttons on the right hand side of the display
	private Node getButtons() {
		VBox v = new VBox(20);
		HBox removeH = new HBox();
		HBox addH = new HBox();
		
		Button newItem, removeItem, details, edit, plusOne, minusOne, plusTen, minusTen;
		
		newItem = new Button("Add Item");
		removeItem = new Button("Remove Item");
		details = new Button("Details");
		edit = new Button("Edit Item");
		plusOne = new Button("+1");
		minusOne = new Button("-1");
		plusTen = new Button("+10");
		minusTen = new Button("-10");
		
		plusOne.setPrefWidth(40);
		plusTen.setPrefWidth(40);
		minusOne.setPrefWidth(40);
		minusTen.setPrefWidth(40);
		
		removeH.getChildren().addAll(minusOne,minusTen);
		addH.getChildren().addAll(plusOne,plusTen);
		
		//setting the button actions
		newItem.setOnAction(e->getAddAction());
		minusOne.setOnAction(e->minusOneToItem());
		plusOne.setOnAction(e->addOneToItem());
		minusTen.setOnAction(e->minusTenToItem());
		plusTen.setOnAction(e->addTenToItem());
		removeItem.setOnAction(e->removeTheItem());
		edit.setOnAction(e->getEditPane());
		
		
		
		v.getChildren().addAll(newItem,addH,removeH,removeItem,edit);
		return v;
	}
	
	//button actions (They are self explanatory)
	private void removeTheItem() {
		items.remove(table.getSelectionModel().getSelectedIndex());
		updateListBox();
	}

	private void addOneToItem() {
		Item item = items.get(table.getSelectionModel().getSelectedIndex());
		item.setQuantity(item.getQuantity()+1);
		table.getItems().set(table.getSelectionModel().getSelectedIndex(), item);
	}

	private void minusOneToItem() {
		Item item = items.get(table.getSelectionModel().getSelectedIndex());
		item.setQuantity(item.getQuantity()-1);
		table.getItems().set(table.getSelectionModel().getSelectedIndex(), item);
	}
	
	private void addTenToItem() {
		Item item = items.get(table.getSelectionModel().getSelectedIndex());
		item.setQuantity(item.getQuantity()+10);
		table.getItems().set(table.getSelectionModel().getSelectedIndex(), item);
	}
	
	private void minusTenToItem() {
		Item item = items.get(table.getSelectionModel().getSelectedIndex());
		item.setQuantity(item.getQuantity()-10);
		table.getItems().set(table.getSelectionModel().getSelectedIndex(), item);
	}

	//calls for a new menu to add a new item
	private void getAddAction() {
		//sets the text to blank so you don't have to erase it yourself each time
		//basically reseting the menu to its default
		addItem.getChildren().clear();
		name.setText("");
		description.setText("");
		quantity.setText("");
		weight.setText("");
		value.setText("");
		d4.setText("");
		d6.setText("");
		d8.setText("");
		d10.setText("");
		d12.setText("");
		range.setSelected(false);
		normalRange.setText("");
		maxRange.setText("");
		properties.getItems().clear();
		propertyEnter.setText("");
		strengthRequirement.setText("");
		armorClass.setText("");
		hasDexMod.setSelected(false);
		maxDex.setText("");
		
		
		normalRange.setDisable(true);
		maxRange.setDisable(true);
		
		addItemStage = new Stage();
		addItemStage.setTitle("Create Item");
		
		HBox p = new HBox();
		
		p.getChildren().addAll(getAddMenu());
		p.setAlignment(Pos.CENTER);
		
		addItemScene = new Scene(p,500,350);
		
		addItemStage.setScene(addItemScene);
		
		addItemStage.show();
	}

	//adds the buttons and displays of the add menu
	private Node getAddMenu() {
		HBox h = new HBox();
		VBox v = new VBox(20);
		v.getChildren().addAll(getRadioButtons(),addItem,getAddButton());
		v.setAlignment(Pos.CENTER);
		h.getChildren().add(v);
		h.setAlignment(Pos.CENTER);
		return h;
	}

	//adds the add button and centers it on the pane
	private Node getAddButton() {
		HBox h = new HBox();
		
		h.getChildren().add(addButton);
		h.setAlignment(Pos.CENTER);
		addButton.setVisible(false);
		addButton.setOnAction(e->getAddButtonAction());
		
		return h;
	}

	//sets the add button actions
	private void getAddButtonAction() {
		//catches if their aren't numbers when it needs numbers in certain fields
		try {
			//translates data from the fields into actual numbers
			//also checks if they are blank and if they are then it sets it as zero
			//basic item
			String itemName = name.getText();
			String itemDescription = description.getText();
			
			double itemValue = 0, itemWeight = 0;
			int itemQuantity = 0;
			if(!value.getText().equals(""))
				itemValue = Double.parseDouble(value.getText());
			if(!weight.getText().equals(""))
				itemWeight = Double.parseDouble(weight.getText());
			if(!quantity.getText().equals(""))
				itemQuantity = Integer.parseInt(quantity.getText());
			
			//weapon
			int d4Int = 0, d6Int = 0, d8Int = 0, d10Int = 0, d12Int = 0;
			if(!d4.getText().equals(""))
				d4Int = Integer.parseInt(d4.getText());
			if(!d6.getText().equals(""))
				d6Int = Integer.parseInt(d6.getText());
			if(!d8.getText().equals(""))
				d8Int = Integer.parseInt(d8.getText());
			if(!d10.getText().equals(""))
				d10Int = Integer.parseInt(d10.getText());
			if(!d12.getText().equals(""))
				d12Int = Integer.parseInt(d12.getText());
			
			int normalRangeInt = 0, maxRangeInt = 0;
			boolean isRanged = range.isSelected();
			if(!normalRange.getText().equals(""))
				normalRangeInt = Integer.parseInt(normalRange.getText());
			if(!maxRange.getText().equals(""))
				maxRangeInt = Integer.parseInt(maxRange.getText());
			
			ArrayList<String> itemProperties = new ArrayList<>(properties.getItems());
			
			//armor
			int strengthReqInt = 0, armorClassInt = 0, maxDexInt = 0;
			boolean isDexMod = hasDexMod.isSelected();
			if(!strengthRequirement.getText().equals(""))
				strengthReqInt = Integer.parseInt(strengthRequirement.getText());
			if(!armorClass.getText().equals(""))
				armorClassInt = Integer.parseInt(armorClass.getText());
			if(!maxDex.getText().equals(""))
				maxDexInt = Integer.parseInt(maxDex.getText());
			
			//takes the translated data and adds the new item
			if(itemSelection == 0) {
				Item i = new Item(itemName,itemDescription,itemValue,itemWeight,itemQuantity);
				items.add(i);
				//l.getItems().add(i);
			}else if(itemSelection == 1) {
				Weapon w = new Weapon(itemName,itemDescription,itemValue,itemWeight,itemQuantity,
						isRanged, normalRangeInt, maxRangeInt, d4Int, d6Int, d8Int, d10Int, d12Int, itemProperties);
				items.add(w);
			}else if(itemSelection == 2) {
				Armor a = new Armor(itemName,itemDescription,itemValue,itemWeight,itemQuantity,
						strengthReqInt,armorClassInt,isDexMod,maxDexInt);
				items.add(a);
			}
			
			updateListBox();
			addItemStage.close();
		}catch(Exception ex) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Invalid Data");
			a.setHeaderText("Invalid Data");
			a.setContentText("Please enter NUMBERS where there should be NUMBERS");
			//ex.printStackTrace();
			a.showAndWait();
		}
		
	}

	//updates the TableView (was previously a list box) to show the new item added or edited
	private void updateListBox() {
		table.setItems(FXCollections.observableArrayList(items));
		
	}

	//adds the radio buttons to the add menu to change the type of item to add
	private Node getRadioButtons() {
		VBox v = new VBox();
		HBox h = new HBox(15);
		ToggleGroup radioGroup = new ToggleGroup();
		RadioButton armor, weapon, misc;
		
		armor = new RadioButton("Armor");
		weapon = new RadioButton("Weapon");
		misc = new RadioButton("Misc");
		
		armor.setToggleGroup(radioGroup);
		weapon.setToggleGroup(radioGroup);
		misc.setToggleGroup(radioGroup);
		
		armor.setOnAction(e->getArmorSelection());
		weapon.setOnAction(e->getWeaponSelection());
		misc.setOnAction(e->getMiscSelection());
		
		h.getChildren().addAll(armor,weapon,misc);
		v.getChildren().add(h);
		v.setAlignment(Pos.CENTER);
		h.setAlignment(Pos.CENTER);
		return v;
	}

	//if a basic item is selected then it changes it to the basic item editor
	private void getMiscSelection() {
		addButton.setVisible(true);
		addItem.getChildren().clear();
		itemSelection = 0;
		
		GridPane grid = new GridPane();
		
		grid.add(new Label("Name:"), 0, 0);
		grid.add(new Label("Value:"), 0, 1);
		grid.add(new Label("Weight:"), 0, 2);
		grid.add(new Label("Quantity:"), 0, 3);
		grid.add(new Label("Description:"), 0, 4);
		
		grid.add(name, 1, 0);
		grid.add(value, 1, 1);
		grid.add(weight, 1, 2);
		grid.add(quantity, 1, 3);
		grid.add(description, 1, 4);
		
		description.setMaxSize(200, 100);
		description.setWrapText(true);
		
		quantity.setMaxWidth(50);
		
		addItemScene.getWindow().setWidth(500);
		
		grid.setHgap(5);
		grid.setVgap(10);
		
		addItem.getChildren().addAll(grid);
	}

	//if a weapon is selected then it changes it to the weapon editor
	private void getWeaponSelection() {
		addButton.setVisible(true);
		addItem.getChildren().clear();
		itemSelection = 1;
		HBox main = new HBox(30);
		
		GridPane grid = new GridPane();
		
		grid.add(new Label("Name:"), 0, 0);
		grid.add(new Label("Value:"), 0, 1);
		grid.add(new Label("Weight:"), 0, 2);
		grid.add(new Label("Quantity:"), 0, 3);
		grid.add(new Label("Description:"), 0, 4);
		
		grid.add(name, 1, 0);
		grid.add(value, 1, 1);
		grid.add(weight, 1, 2);
		grid.add(quantity, 1, 3);
		grid.add(description, 1, 4);
		
		description.setMaxSize(200, 100);
		description.setWrapText(true);
		
		quantity.setMaxWidth(50);
		
		VBox v = new VBox(10);
		
		GridPane grid2 = new GridPane();
		
		grid2.add(new Label("d4:"), 0, 0);
		grid2.add(new Label("d6:"), 0, 1);
		grid2.add(new Label("d8:"), 0, 2);
		grid2.add(new Label("d10:"), 0, 3);
		grid2.add(new Label("d12:"), 0, 4);
		
		grid2.add(d4, 1, 0);
		grid2.add(d6, 1, 1);
		grid2.add(d8, 1, 2);
		grid2.add(d10, 1, 3);
		grid2.add(d12, 1, 4);
		
		
		d4.setPrefWidth(30);
		d6.setPrefWidth(30);
		d8.setPrefWidth(30);
		d10.setPrefWidth(30);
		d12.setPrefWidth(30);
		
		GridPane grid3 = new GridPane();
		
		grid3.add(range, 0, 0);
		grid3.add(new Label("Normal Range:"), 0, 1);
		grid3.add(new Label("Max Range:"), 0, 2);
		
		grid3.add(normalRange, 1, 1);
		grid3.add(maxRange, 1, 2);
		
		normalRange.setPrefWidth(35);
		maxRange.setPrefWidth(35);
		
		grid.setHgap(5);
		grid.setVgap(10);
		
		grid2.setHgap(5);
		grid2.setVgap(10);
		
		grid3.setHgap(5);
		grid3.setVgap(10);
		
		v.getChildren().addAll(grid2, grid3);
		
		main.getChildren().addAll(grid, v);
		
		addItemScene.getWindow().setWidth(725);
		
		setRangedCheckAction();
		getPropertyListView(main);
		
		addItem.getChildren().addAll(main);
	}

	//sets it so that you can only edit the range if the ranged property if checked
	private void setRangedCheckAction() {
		range.setOnAction(e->{
			if(range.isSelected()) {
				normalRange.setDisable(false);
				maxRange.setDisable(false);
			}else {
				normalRange.setDisable(true);
				maxRange.setDisable(true);
			}
		});
		
	}

	//adds the list view to add/edit all of the properties that the weapon has
	private void getPropertyListView(HBox main) {
		GridPane grid = new GridPane();
		HBox topH = new HBox(5);
		HBox bottomH = new HBox(5);
		
		Button addProperty = new Button("Add");
		Button removeProperty = new Button("Remove");
		
		addProperty.setMaxWidth(100);
		removeProperty.setMaxWidth(100);
		properties.setMaxHeight(150);
		properties.setMaxWidth(205);
		propertyEnter.setMaxWidth(120);
		
		addProperty.setOnAction(e->{
			properties.getItems().add(propertyEnter.getText());
			propertyEnter.setText("");
		});
		removeProperty.setOnAction(e->{
			//checks to see if there are any more items in the ListView to remove
			try {
				properties.getItems().remove(properties.getSelectionModel().getSelectedIndex());
			}catch(ArrayIndexOutOfBoundsException ex) {
				
			}
		});
		
		bottomH.getChildren().addAll(addProperty, removeProperty);
		
		topH.getChildren().addAll(new Label("Enter Property:"), propertyEnter);
		
		grid.add(topH, 0, 0);
		grid.add(bottomH, 0, 1);
		grid.add(properties, 0, 2);
		
		grid.setVgap(10);
		
		main.getChildren().add(grid);
		
	}

	//if armor is selected then it changes it to the armor editor
	private void getArmorSelection() {
		addButton.setVisible(true);
		addItem.getChildren().clear();
		itemSelection = 2;
		
		HBox main = new HBox(15);
		
		GridPane grid = new GridPane();
		
		grid.add(new Label("Name:"), 0, 0);
		grid.add(new Label("Value:"), 0, 1);
		grid.add(new Label("Weight:"), 0, 2);
		grid.add(new Label("Quantity:"), 0, 3);
		grid.add(new Label("Description:"), 0, 4);
		
		grid.add(name, 1, 0);
		grid.add(value, 1, 1);
		grid.add(weight, 1, 2);
		grid.add(quantity, 1, 3);
		grid.add(description, 1, 4);
		
		GridPane grid2 = new GridPane();
		
		grid2.add(new Label("Strength Requirement:"), 0, 0);
		grid2.add(new Label("Armor Class:"), 0, 1);
		grid2.add(hasDexMod, 0, 2);
		grid2.add(new Label("Dex Max:"), 0, 3);
		
		grid2.add(strengthRequirement, 1, 0);
		grid2.add(armorClass, 1, 1);
		grid2.add(maxDex, 1, 3);
		
		grid.setHgap(5);
		grid.setVgap(10);
		
		grid2.setHgap(5);
		grid2.setVgap(10);
		
		main.getChildren().addAll(grid, grid2);
		
		setHasDexModAction();
		
		description.setMaxSize(200, 100);
		description.setWrapText(true);
		
		addItemScene.getWindow().setWidth(600);
		
		quantity.setMaxWidth(30);
		strengthRequirement.setMaxWidth(30);
		armorClass.setMaxWidth(30);
		maxDex.setMaxWidth(30);
		maxDex.setDisable(true);
		
		addItem.getChildren().addAll(main);
	}
	
	//sets the maxDex disabled if the hasDexMod CheckBox is not checked
	private void setHasDexModAction() {
		hasDexMod.setOnAction(e->{
			maxDex.setDisable(!hasDexMod.isSelected());
		});
		
	}
	
	//adds the edit pane 
	private void getEditPane() {
		
		//if the item is a basic item then it will bring up the edit menu
		//but it will only allow you to edit it as a basic item
		//same with the rest of the itme types
		if(((Item)table.getSelectionModel().getSelectedItem()).type.equals("Item")) {
			itemSelection = 0;
			getItemEditMenu();
			getMiscSelection();
			System.out.println("Item");
		}else if(((Item)table.getSelectionModel().getSelectedItem()).type.equals("Weapon")) {
			itemSelection = 1;
			getWeaponEditMenu();
			getWeaponSelection();
		}else if(((Item)table.getSelectionModel().getSelectedItem()).type.equals("Armor")) {
			itemSelection = 2;
			getArmorEditMenu();
			getArmorSelection();
		}
		
		
	}

	//same as the armor add menu but fills in the fields
	private void getArmorEditMenu() {
		//fills in the fields with the selected item
		Armor item = (Armor) table.getSelectionModel().getSelectedItem();
		addItem.getChildren().clear();
		name.setText(item.getName());
		description.setText(item.getDescription());
		quantity.setText(""+item.getQuantity());
		weight.setText(""+item.getWeight());
		value.setText(""+item.getValue());
		
		strengthRequirement.setText(""+item.getStrengthRequirement());
		armorClass.setText(""+item.getaC());
		hasDexMod.setSelected(item.isDexMod());;
		maxDex.setText(""+item.getDexMax());
		
		addItemStage = new Stage();
		addItemStage.setTitle("Edit Item");
		
		HBox p = new HBox();
		
		p.getChildren().addAll(getEditMenu());
		p.setAlignment(Pos.CENTER);
		
		addItemScene = new Scene(p,500,350);
		
		addItemStage.setScene(addItemScene);
		
		addItemStage.show();
		
	}

	private void getWeaponEditMenu() {
		Weapon item = (Weapon) table.getSelectionModel().getSelectedItem();
		addItem.getChildren().clear();
		name.setText(item.getName());
		description.setText(item.getDescription());
		quantity.setText(""+item.getQuantity());
		weight.setText(""+item.getWeight());
		value.setText(""+item.getValue());
		
		d4.setText(""+item.getNumD4());
		d6.setText(""+item.getNumD6());
		d8.setText(""+item.getNumD8());
		d10.setText(""+item.getNumD10());
		d12.setText(""+item.getNumD12());
		range.setSelected(item.isRanged());
		normalRange.setText(""+item.getNormalRange());
		maxRange.setText(""+item.getMaxRange());
		properties.setItems(FXCollections.observableArrayList(item.getProperties()));
		
		addItemStage = new Stage();
		addItemStage.setTitle("Edit Item");
		
		HBox p = new HBox();
		
		p.getChildren().addAll(getEditMenu());
		p.setAlignment(Pos.CENTER);
		
		addItemScene = new Scene(p,500,350);
		
		addItemStage.setScene(addItemScene);
		
		addItemStage.show();
		
	}

	private void getItemEditMenu() {
		Item item = (Item) table.getSelectionModel().getSelectedItem();
		addItem.getChildren().clear();
		name.setText(item.getName());
		description.setText(item.getDescription());
		quantity.setText(""+item.getQuantity());
		weight.setText(""+item.getWeight());
		value.setText(""+item.getValue());
		
		addItemStage = new Stage();
		addItemStage.setTitle("Edit Item");
		
		HBox p = new HBox();
		
		p.getChildren().addAll(getEditMenu());
		p.setAlignment(Pos.CENTER);
		
		addItemScene = new Scene(p,500,350);
		
		addItemStage.setScene(addItemScene);
		
		addItemStage.show();
		
	}

	//adds the same things as the add menu but leaves out the radio buttons
	private Node getEditMenu() {
		HBox h = new HBox();
		VBox v = new VBox(20);
		v.getChildren().addAll(addItem,getEditButton());
		v.setAlignment(Pos.CENTER);
		h.getChildren().add(v);
		h.setAlignment(Pos.CENTER);
		return h;
	}

	//destroys the selected item and replaces it with then does the same thin as then calls the add action
	private Node getEditButton() {
		Button b = new Button("Edit");
		b.setOnAction((e)->{
			items.remove(table.getSelectionModel().getSelectedIndex());
			getAddButtonAction();
		});
		
		return b;
	}
}
