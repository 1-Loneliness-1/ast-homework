package Builder;

public class Person {

    private String name;
    private String lastName;
    private Integer age;

    private String middleName;
    private String email;
    private String phone;

    private Person() {}

    public static class Builder {

        private final String name;
        private final String lastName;
        private final Integer age;

        private String middleName;
        private String email;
        private String phone;

        public Builder (String name, String lastName, Integer age) {
            this.name = name;
            this.lastName = lastName;
            this.age = age;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Person build() {
            Person personForReturn = new Person();

            personForReturn.name = this.name;
            personForReturn.lastName = this.lastName;
            personForReturn.age = this.age;
            personForReturn.middleName = this.middleName;
            personForReturn.email = this.email;
            personForReturn.phone = this.phone;

            return personForReturn;
        }

    }

}
