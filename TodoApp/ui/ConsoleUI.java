package TodoApp.ui;

import TodoApp.model.Task;
import TodoApp.service.TaskService;
import TodoApp.storage.FileStorage;

import java.util.List;
import java.util.Scanner;
public class ConsoleUI {
    private TaskService service = new TaskService();
    private FileStorage storage = new FileStorage();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        List<Task> loadedTasks = storage.loadTasks();
        for (Task task : loadedTasks) {
            service.addTask(task.getTitle(), task.getDescription());
        }

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3: 
                    editTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    markComplete();
                    break;
                case 6:
                    exitApp();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== TO-DO LIST MENU ===");
        System.out.println("1. Thêm công việc");
        System.out.println("2. Xem tất cả công việc");
        System.out.println("3. Sửa công việc");
        System.out.println("4. Xoá công việc");
        System.out.println("5. Đánh dấu hoàn thành");
        System.out.println("6. Thoát");
        System.out.print("Chọn: ");
    }

    private void addTask() {
        System.out.print("Tiêu đề: ");
        String title = scanner.nextLine();
        System.out.print("Mô tả: ");
        String desc = scanner.nextLine();
        service.addTask(title, desc);
    }

    private void viewTasks() {
        List<Task> tasks = service.getAllTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void editTask() {
        System.out.print("Nhập ID công việc cần sửa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tiêu đề mới: ");
        String title = scanner.nextLine();
        System.out.print("Mô tả mới: ");
        String desc = scanner.nextLine();
        if (!service.editTask(id, title, desc)) {
            System.out.println("Không tìm thấy công việc.");
        }
    }

    private void deleteTask() {
        System.out.print("Nhập ID cần xoá: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (!service.deleteTask(id)) {
            System.out.println("Không tìm thấy công việc.");
        }
    }

    private void markComplete() {
        System.out.print("Nhập ID để đánh dấu hoàn thành: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (!service.markCompleted(id)) {
            System.out.println("Không tìm thấy công việc.");
        }
    }

    private void exitApp() {
        storage.saveTasks(service.getAllTasks());
        System.out.println("Đã lưu và thoát.");
        System.exit(0);
    }
}
