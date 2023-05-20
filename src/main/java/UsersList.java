public class UsersList {
  private final Integer id;
  private final String name;
  private final String link;

  public UsersList(Integer id, String name, String link) {
    this.id = id;
    this.name = name;
    this.link = link;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLink() {
    return link;
  }

}
