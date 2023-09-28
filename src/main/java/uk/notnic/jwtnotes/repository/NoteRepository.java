package uk.notnic.jwtnotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.notnic.jwtnotes.model.Note;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
  List<Note> findAllByCreatedBy(String createdBy);
}
