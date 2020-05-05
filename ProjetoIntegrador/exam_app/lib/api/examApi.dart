import 'package:examapp/api/apiService.dart';
import 'package:examapp/models/Exam.dart';
import 'package:examapp/models/User.dart';

class ExamApi {
  final ApiService _apiService = ApiService();

  createAccount(User user, Function onSuccess, Function onError) async {
    await _apiService.doPost(
        "/sign-up",
        {
          "name": user.name,
          "username": user.username,
          "password": user.password
        },
        onSuccess,
        onError);
  }

  login(User user, Function onSuccess, Function onError) async {
    await _apiService.doPost(
        "/authenticate",
        {"username": user.username, "password": user.password},
        onSuccess,
        onError);
  }

  getCurrentUser(Function onSuccess, Function onError) async {
    await _apiService.doGet("/user/current", onSuccess, onError);
  }

  createExam(Exam exam, Function onSuccess, Function onError) async {
    await _apiService.doPost("/api/exams",
        {"date": exam.date, "glucose": exam.glucose}, onSuccess, onError);
  }

  getMyExams(Function onSuccess, Function onError) async {
    await _apiService.doGet("/api/exams", onSuccess, onError);
  }

  removeExam(int id, Function onSuccess, Function onError) async {
    await _apiService.doDelete("/api/exams", id, onSuccess, onError);
  }
}
