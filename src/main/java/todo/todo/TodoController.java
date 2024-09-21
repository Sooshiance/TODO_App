package todo.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

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

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateItem(@PathVariable Long id, @RequestBody TodoItem itemDetails) {
        Optional<TodoItem> optionalTodo = todoItemRepository.findById(id);

        if (optionalTodo.isPresent()) {
            TodoItem existingTodo = optionalTodo.get();
            existingTodo.setTitle(itemDetails.getTitle());
            existingTodo.setCompleted(itemDetails.isCompleted());

            final TodoItem updatedTodo = todoItemRepository.save(existingTodo);
            return ResponseEntity.ok(updatedTodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        Optional<TodoItem> optionalTodo = todoItemRepository.findById(id);

        if (optionalTodo.isPresent()) {
            todoItemRepository.delete(optionalTodo.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
