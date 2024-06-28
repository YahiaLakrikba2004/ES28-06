package YahiaLakrikba.ES_28._6.repositories;

import YahiaLakrikba.ES_28._6.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeviceRepository extends JpaRepository<Device,Integer>, PagingAndSortingRepository<Device,Integer> {
}
