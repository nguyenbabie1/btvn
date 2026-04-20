package org.example.sesoin10_45p.controller;

import jakarta.validation.Valid;
import org.example.sesoin10_45p.model.TaskItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class TaskController {

    private final List<TaskItem> tasks = new ArrayList<>();

    public TaskController() {
        TaskItem task1 = new TaskItem();
        task1.setId("T-001");
        task1.setTitle("Hoan thanh bai tap Spring MVC");
        task1.setDeadline(LocalDate.now().plusDays(7));
        task1.setPriority("HIGH");

        TaskItem task2 = new TaskItem();
        task2.setId("T-002");
        task2.setTitle("Doc tai lieu Thymeleaf");
        task2.setDeadline(LocalDate.now().plusDays(14));
        task2.setPriority("MEDIUM");

        TaskItem task3 = new TaskItem();
        task3.setId("T-003");
        task3.setTitle("On tap validation va form");
        task3.setDeadline(LocalDate.now().plusDays(3));
        task3.setPriority("LOW");

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/tasks/new")
    public String showTaskForm(Model model) {
        model.addAttribute("taskItem", new TaskItem());
        return "task-form";
    }

    @PostMapping("/tasks")
    public String createTask(
            @Valid @ModelAttribute("taskItem") TaskItem taskItem,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "task-form";
        }

        taskItem.setId("T-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        tasks.add(taskItem);
        return "redirect:/tasks";
    }
}
