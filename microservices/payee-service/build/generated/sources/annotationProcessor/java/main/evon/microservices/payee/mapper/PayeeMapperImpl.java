package evon.microservices.payee.mapper;

import evon.api.userauth.dto.PayeeRequestDto;
import evon.api.userauth.models.Payee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-04T12:21:21+0530",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.2.jar, environment: Java 17.0.6 (Private Build)"
)
@Component
public class PayeeMapperImpl implements PayeeMapper {

    @Override
    public Payee requestDtoToPayee(PayeeRequestDto payeeRequestDto) {
        if ( payeeRequestDto == null ) {
            return null;
        }

        Payee.PayeeBuilder payee = Payee.builder();

        payee.name( payeeRequestDto.getName() );
        payee.status( payeeRequestDto.getStatus() );
        payee.address( payeeRequestDto.getAddress() );
        payee.city( payeeRequestDto.getCity() );
        payee.state( payeeRequestDto.getState() );
        payee.pinCode( payeeRequestDto.getPinCode() );
        payee.country( payeeRequestDto.getCountry() );
        payee.panNumber( payeeRequestDto.getPanNumber() );
        payee.indianCitizen( payeeRequestDto.getIndianCitizen() );
        payee.chargeSurcharge( payeeRequestDto.getChargeSurcharge() );
        payee.isEmployee( payeeRequestDto.getIsEmployee() );
        payee.organization( payeeRequestDto.getOrganization() );

        return payee.build();
    }

    @Override
    public PayeeRequestDto payeeToRequestDto(Payee payee) {
        if ( payee == null ) {
            return null;
        }

        PayeeRequestDto payeeRequestDto = new PayeeRequestDto();

        payeeRequestDto.setName( payee.getName() );
        payeeRequestDto.setStatus( payee.getStatus() );
        payeeRequestDto.setAddress( payee.getAddress() );
        payeeRequestDto.setCity( payee.getCity() );
        payeeRequestDto.setState( payee.getState() );
        payeeRequestDto.setPinCode( payee.getPinCode() );
        payeeRequestDto.setCountry( payee.getCountry() );
        payeeRequestDto.setPanNumber( payee.getPanNumber() );
        payeeRequestDto.setIndianCitizen( payee.getIndianCitizen() );
        payeeRequestDto.setChargeSurcharge( payee.getChargeSurcharge() );
        payeeRequestDto.setIsEmployee( payee.getIsEmployee() );
        payeeRequestDto.setOrganization( payee.getOrganization() );

        return payeeRequestDto;
    }
}
