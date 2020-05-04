class Exam {
  int id;
  String date;
  String glucose;

  Exam();

  Exam.fromJson(Map jsonMap)
      : id = jsonMap['id'],
        date = jsonMap['date'],
        glucose = jsonMap['glucose'];
}
