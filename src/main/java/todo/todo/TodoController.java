package todo.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/")
    public List<TodoItem> getAllItems() {
        return todoItemRepository.findAll();
    }

    @PostMapping("/")
    public TodoItem addItem(@RequestBody TodoItem item) {
        return todoItemRepository.save(item);
    }

    // Implement update and delete endpoints as needed
}
