package Adapter;

public class OldUserClass {

    private final String name;
    private final String surname;
    private final String middleName;
    private final String ageInXXyoFormat;

    public OldUserClass(String name, String surname, String middleName, String ageInXXyoFormat) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.ageInXXyoFormat = ageInXXyoFormat;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getAgeInXXyoFormat() {
        return ageInXXyoFormat;
    }

}
