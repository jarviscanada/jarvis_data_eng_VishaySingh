package ca.jrvs.apps.trading.domain;

public class Trader implements Entity<Integer> {

  /* ATTRIBUTES
  country	string
  dob	string($date-time)
  email	string
  firstName	string
  id	integer($int32)
  lastName	string
   */
  private Integer id;
  private String country;
  private String email;
  private String firstName;
  private String lastName;
  private String dob;

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer integer) {
    id = integer;
  }
}
