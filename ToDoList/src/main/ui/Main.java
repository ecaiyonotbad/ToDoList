package ui;

import ui.todolist.ToDoList;

import java.io.IOException;

public class Main {

    //EFFECTS: Initiate the entire program
    public static void main(String[] args) throws IOException {
        ToDoList list = new ToDoList();
        list.run();
    }
}
