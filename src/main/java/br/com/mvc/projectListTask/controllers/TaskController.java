package br.com.mvc.projectListTask.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mvc.projectListTask.entities.Task;
import br.com.mvc.projectListTask.entities.UserDtls;
import br.com.mvc.projectListTask.entities.enums.TaskStatus;
import br.com.mvc.projectListTask.repositories.UserRepository;
import br.com.mvc.projectListTask.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasksHtml/task-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") Task task, RedirectAttributes attributes, Principal principal) {
        String username = principal.getName();
        UserDtls user = userRepository.findByEmail(username);
        taskService.saveTaskAndUser(task, user);
        attributes.addFlashAttribute("mensagem", "Tarefa cadastrada com sucesso!");
        return "redirect:/tasks/new";
    }

    @GetMapping("/list")
    public String listTasks(Model model, Principal principal) {
        String username = principal.getName();
        UserDtls user = userRepository.findByEmail(username);
        List<Task> tasks = taskService.getTasksByUser(user);
        model.addAttribute("tasks", tasks);
        return "tasksHtml/task-list";
    }

    @GetMapping("/detalhe/{id}")
    public ModelAndView detalhesVaga(@PathVariable("id") Long id, Principal principal) {
        Task task = taskService.getTaskById(id);
        String username = principal.getName();
        UserDtls user = userRepository.findByEmail(username);

        // Verificar se o usuário logado é o proprietário da tarefa
        if (!task.getUser().equals(user)) {
            // Redirecionar para a página de erro
            ModelAndView mv = new ModelAndView("errorTaskNotYour");
            return mv;
        }

        ModelAndView mv = new ModelAndView("tasksHtml/details-task");
        mv.addObject("task", task);
        return mv;
    }

    @PostMapping("/detalhe/{id}")
    public String atualizarStatusTarefa(@PathVariable("id") Long id, @RequestParam("status") String status,
            RedirectAttributes attributes) {
        TaskStatus taskStatus = TaskStatus.valueOf(status);
        taskService.saveTaskStatus(id, taskStatus);
        attributes.addFlashAttribute("mensagem", "Status da tarefa atualizado com sucesso!");
        return "redirect:/tasks/detalhe/" + id;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        String username = principal.getName();
        UserDtls user = userRepository.findByEmail(username);

        // Verificar se o usuário logado é o proprietário da tarefa
        if (!task.getUser().equals(user)) {
            // Redirecionar ou exibir uma mensagem de erro
            return "errorTaskNotYour";
        }

        model.addAttribute("task", task);
        return "tasksHtml/task-edit";
    }
    
    @PostMapping("/update")
    public String updateTask(@ModelAttribute("task") Task task, RedirectAttributes attributes) {
        taskService.updateTask(task);
        attributes.addFlashAttribute("mensagem", "Tarefa alterada com sucesso!");
        return "redirect:/tasks/edit/" + task.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks/list";
    }

    @GetMapping("/{status}")
    public String porStatus(@PathVariable("status") String status, Model model, Principal principal) {
        String username = principal.getName();
        UserDtls user = userRepository.findByEmail(username);
        
        List<Task> tasks;
        if (status.equalsIgnoreCase("pendente")) {
            tasks = taskService.getTasksByStatusAndUser(TaskStatus.PENDENTE, user);
        } else if (status.equalsIgnoreCase("atrasada")) {
            tasks = taskService.getTasksByStatusAndUser(TaskStatus.ATRASADA, user);
        } else if (status.equalsIgnoreCase("concluida")) {
            tasks = taskService.getTasksByStatusAndUser(TaskStatus.CONCLUIDA, user);
        } else {
            return "redirect:/tasks/list";
        }
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("status", status);
        return "tasksHtml/task-list";
    }
}
