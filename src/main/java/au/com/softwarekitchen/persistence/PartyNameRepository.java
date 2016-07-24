package au.com.softwarekitchen.persistence;

import au.com.softwarekitchen.model.Name;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PartyNameRepository extends PagingAndSortingRepository<Name, Long> {

    List<Name> findByPartyId(Long partyId);

}
