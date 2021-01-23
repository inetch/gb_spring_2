package ru.gb.inetch.shoppee.repositories;


import ru.gb.inetch.shoppee.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
}
