package core.basesyntax.service;

import core.basesyntax.appexception.RegisterException;
import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_LENGTH = 6;
    private static final int MIN_AGE = 18;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getLogin() == null || user.getLogin().length() < 6) {
            throw new RegisterException("User login should not be NULL or LESS than 6 letter");
        }
        for (User record: Storage.people) {
            if (user.getLogin().equals(record.getLogin())) {
                throw new RegisterException("There is already user with such login");
            }
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new RegisterException("User password should not be NULL or LESS than 6 letter");
        }
        if (user.getAge() < MIN_AGE) {
            throw new RegisterException("user's age can't be LESS than 18");
        }

        Storage.people.add(user);

        return user;
    }
}
