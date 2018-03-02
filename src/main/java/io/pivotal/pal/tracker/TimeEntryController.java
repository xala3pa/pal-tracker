package io.pivotal.pal.tracker;

import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

  private TimeEntryRepository timeEntryRepository;

  public TimeEntryController(TimeEntryRepository timeEntryRepository) {
    this.timeEntryRepository = timeEntryRepository;
  }

  @PostMapping()
  public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
    return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<TimeEntry> read(@PathVariable long id) {
    TimeEntry timeEntry = timeEntryRepository.find(id);
    if (timeEntry != null) {
      return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping()
  public ResponseEntity<List<TimeEntry>> list() {
    return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
    TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
    if (updatedTimeEntry != null) {
      return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
    timeEntryRepository.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
