
package lab.mvc.model;

public class Person {

    private String firstName;
    private String lastName;
    private String birthDay;
    private String sex;

    // Constructeur avec les 4 attributs
    public Person(String firstName, String lastName,
                  String birthDay, String sex) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.sex = sex;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getSex() {
        return sex;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
