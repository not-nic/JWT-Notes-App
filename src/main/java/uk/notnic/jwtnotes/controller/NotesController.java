package uk.notnic.jwtnotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uk.notnic.jwtnotes.model.Note;
import uk.notnic.jwtnotes.service.NotesService;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

  private final NotesService notesService;

  @Autowired
  public NotesController(NotesService notesService) {
    this.notesService = notesService;
  }

  @DeleteMapping("/{id}")
  public void deleteNoteById(@PathVariable long id, Authentication authentication) throws AccessDeniedException {
    notesService.deleteNoteById(id, authentication.getName());
  }

  @PostMapping("")
  public String createNote(Authentication authentication, @RequestBody NoteRequest noteRequest) {
    System.out.println(authentication.toString());
    notesService.createNote(noteRequest, authentication.getName());
    return String.format("Note created by %s", authentication.getName());
  }

  @GetMapping("")
  public List<Note> getUserNotes(Authentication authentication) {
    return notesService.getUserNotes(authentication.getName());
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  public List<Note> getNotes() {
    return notesService.getNotes();
  }
}
