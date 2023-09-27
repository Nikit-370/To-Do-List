package ToDoListProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

	static List<String> itemList = new ArrayList<String>();
	static List<String> deletedItems = new ArrayList<String>();
	static boolean isListSaved = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String item = sc.nextLine();

			if (item.equalsIgnoreCase("savelist")) {
				saveList();
			} else if (item.equalsIgnoreCase("exit")) {
				break;
			} else if (item.equalsIgnoreCase("showitems")) {
				if (isListSaved) {
					showItems();
				} else {
					System.out.println("Save the list first.");
				}
			} else if (item.startsWith("modify")) {
				String modify = item.replace("modify", "").trim();
				String newValue = sc.nextLine();
				modifyItem(modify, newValue);
			} else if (item.isEmpty()) {
				System.err.println("Item can't be empty.");
			} else if (item.equalsIgnoreCase("delete_all")) {
				deleteAllItems();
			} else if (item.startsWith("delete")) {
				String itemToDelete = item.replace("delete", "").trim();
				deleteItem(itemToDelete);
			} else if (item.equalsIgnoreCase("undo")) {
				undoDelete();
			} else {
				itemList.add(item);
				isListSaved = false;
				System.out.println("Item added: " + item);
			}
		}
	}

	public static void saveList() {
		isListSaved = true;
		System.out.println("List saved.");
	}

	public static void showItems() {
		if (itemList.isEmpty()) {
			System.out.println("The list is empty.");
		} else {
			int cnt = 1;
			for (String item : itemList) {
				System.out.println(cnt + ": " + item);
				cnt++;
			}
		}
	}

	public static void deleteItem(String itemToDelete) {
		if (itemList.contains(itemToDelete)) {
			deletedItems.add(itemList.remove(itemList.indexOf(itemToDelete)));
			isListSaved = false;
			System.out.println("Item removed: " + itemToDelete);
		} else {
			System.out.println("Item is not present in the list.");
		}
	}

	public static void modifyItem(String modify, String newValue) {
		if (itemList.contains(modify)) {
			for (String item : itemList) {
				item.equals(modify);
				item.replace(item, newValue);
			}
			isListSaved = false;
			System.out.println("Item modifed: " + newValue);
		} else {
			System.out.println("Item is not present in the list.");
		}
	}

	public static void deleteAllItems() {
		if (!itemList.isEmpty()) {
			deletedItems.addAll(itemList);
			itemList.clear();
			isListSaved = false;
			System.out.println("All items removed");
		} else {
			System.out.println("The list is already empty.");
		}
	}

	public static void undoDelete() {
		if (!deletedItems.isEmpty()) {
			itemList.addAll(deletedItems);
			deletedItems.clear();
			isListSaved = false;
			System.out.println("Undo successful.");
		} else {
			System.out.println("No items to undo.");
		}
	}
}
