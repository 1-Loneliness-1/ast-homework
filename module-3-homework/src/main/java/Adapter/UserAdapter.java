package Adapter;

public class UserAdapter implements ModernUserInterface {

    private final OldUserClass oldUser;

    public UserAdapter(OldUserClass oldUser) {
        this.oldUser = oldUser;
    }

    @Override
    public String getFullName() {
        return String.format("%s %s %s", oldUser.getName(), oldUser.getMiddleName(), oldUser.getSurname());
    }

    @Override
    public int getAge() {
        return Integer.parseInt(oldUser.getAgeInXXyoFormat().split("\\s")[0]);
    }

}
