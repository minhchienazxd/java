package TodoApp.service;

import TodoApp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> taskList = new ArrayList<>();
    private int nextId = 1;

    public void addTask(String title, String description) {
        Task task = new Task(nextId++, title, description);
        taskList.add(task);
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public Task getTaskById(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) return task;
        }
        return null;
    }

    public boolean deleteTask(int id) {
        return taskList.removeIf(task -> task.getId() == id);
    }

    public boolean markCompleted(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setCompleted(true);
            return true;
        }
        return false;
    }

    public boolean editTask(int id, String newTitle, String newDescription) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            return true;
        }
        return false;
    }
}
