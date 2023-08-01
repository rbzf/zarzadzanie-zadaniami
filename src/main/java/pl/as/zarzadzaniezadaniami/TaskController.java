package pl.as.zarzadzaniezadaniami;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    final private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @RequestMapping("/")
    public String index(Model model) {
        List<Task> allTasks = taskRepository.findAll();
        model.addAttribute("allTasks", allTasks);
        model.addAttribute("taskToAdd", new Task());
        return "index";
    }

    @GetMapping("/tasksToDo")
    public String tasksToDo(Model model) {
        List<Task> tasksToDo = new ArrayList<>();
        List<Task> allTasks = taskRepository.findAll();
        allTasks.forEach(task -> {
            if (task.getStatus().equals(Status.NOT_YET_STARTED) || task.getStatus().equals(Status.STARTED))
                tasksToDo.add(task);
            });
        model.addAttribute("tasksToDo", tasksToDo);
        return "tasksToDo";
    }

    @GetMapping("/archive")
    public String tasksCompleted(Model model, @RequestParam(defaultValue = "COMPLETED") Status status) {
        List<Task> tasksCompleted = null;
        if (status.equals(Status.COMPLETED)) {
            tasksCompleted = taskRepository.findByStatus(status);
        }
        model.addAttribute("tasksCompleted", tasksCompleted);
        return "archive";
    }
    @GetMapping("/addNewTask")
    public String editForm(Model model) {
        Task taskToAdd = new Task();
        model.addAttribute("taskToAdd", taskToAdd);
        return "form";
    }
    
    @PostMapping("/add")
    public String addNewTask(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

}
