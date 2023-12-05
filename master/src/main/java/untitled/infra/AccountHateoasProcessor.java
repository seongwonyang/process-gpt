package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class AccountHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Account>> {

    @Override
    public EntityModel<Account> process(EntityModel<Account> model) {
        return model;
    }
}
