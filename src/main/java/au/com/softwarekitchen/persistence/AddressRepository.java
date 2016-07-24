package au.com.softwarekitchen.persistence;

import au.com.softwarekitchen.model.Address;
import au.com.softwarekitchen.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

    List<Customer> findByPartyId(Long partyId);
}
