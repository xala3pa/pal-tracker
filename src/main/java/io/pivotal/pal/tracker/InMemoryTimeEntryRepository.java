package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
  private Map<Long,TimeEntry> inMemoryDb = new HashMap<>();

  @Override
  public TimeEntry create(TimeEntry timeEntry) {
    timeEntry.setId((long)inMemoryDb.size() + 1);
    inMemoryDb.put(timeEntry.getId(),timeEntry);
    return timeEntry;
  }

  @Override
  public TimeEntry find(Long id) {
    return inMemoryDb.get(id);
  }

  @Override
  public TimeEntry update(Long id, TimeEntry timeEntry) {
    inMemoryDb.put(id, timeEntry);
    timeEntry.setId(id);
    return timeEntry;
  }

  @Override
  public void delete(Long id) {
    inMemoryDb.remove(id);
  }

  @Override
  public List<TimeEntry> list() {
    return new ArrayList<>(inMemoryDb.values());
  }
}
