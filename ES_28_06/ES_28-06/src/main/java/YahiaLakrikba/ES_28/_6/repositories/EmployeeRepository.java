package YahiaLakrikba.ES_28._6.repositories;

import YahiaLakrikba.ES_28._6.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>, PagingAndSortingRepository<Employee,Integer> {
}
