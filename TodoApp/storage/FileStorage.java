package TodoApp.storage;

import TodoApp.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
     private final String filePath = "tasks.txt";

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.getId() + ";" + task.getTitle() + ";" +
                        task.getDescription() + ";" + task.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String description = parts[2];
                boolean completed = Boolean.parseBoolean(parts[3]);

                Task task = new Task(id, title, description);
                task.setCompleted(completed);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Không tìm thấy file dữ liệu, tạo mới.");
        }
        return tasks;
    }
}
