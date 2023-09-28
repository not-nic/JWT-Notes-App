package uk.notnic.jwtnotes.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.notnic.jwtnotes.controller.NoteRequest;
import uk.notnic.jwtnotes.model.Note;
import uk.notnic.jwtnotes.repository.NoteRepository;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService {
  private final NoteRepository noteRepository;

  @Autowired
  public NotesService(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  public List<Note> getUserNotes(String author) {
    return noteRepository.findAllByCreatedBy(author);
  }

  public void deleteNoteById(Long id, String author) throws AccessDeniedException, EntityNotFoundException {

    Optional<Note> foundItem = noteRepository.findById(id);

    // if note is found.
    if (foundItem.isPresent()) {
      Note note = foundItem.get();

      // check if the note is created by the incoming author.
      if (note.getCreatedBy().equals(author)) {
        // username valid, delete note by the ID
        noteRepository.deleteById(id);
      } else {
        // throw exception that user is not allowed to delete that note.
        throw new AccessDeniedException("You do not have permission to delete this note.");
      }
    } else {
      // throw exception that note is not found.
      throw new EntityNotFoundException(String.format("Note %s not found", id));
    }
  }

  public void createNote(NoteRequest noteContent, String author) {

    Note note = new Note();

    note.setCreatedAt(LocalDateTime.now());
    note.setCreatedBy(author);
    note.setContent(noteContent.getContent());
    noteRepository.save(note);
  }

  public List<Note> getNotes() {
    return (List<Note>) noteRepository.findAll();
  }
}
