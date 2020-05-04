class User {
  int id;
  String name;
  String username;
  String password;

  User();

  User.fromJson(Map jsonMap)
      : id = jsonMap['id'],
        name = jsonMap['name'],
        username = jsonMap['username'],
        password = jsonMap['password'];
}
