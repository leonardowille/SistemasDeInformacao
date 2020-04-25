import 'package:dio/dio.dart';
import 'package:examapp/api/apiService.dart';
import 'package:examapp/models/User.dart';
import 'package:examapp/models/examResponse.dart';
import 'package:examapp/responseTypeEnum.dart';

class ExamApi {
  final ApiService _apiService = ApiService();

  ExamResponse _getExamResponse(Response response) {
    ExamResponse examResponse = ExamResponse();
    examResponse.response = response;
    if (response.statusCode >= 200 && response.statusCode < 300) {
      examResponse.responseType = ResponseTypeEnum.SUCCESS;
    } else {
      examResponse.responseType = ResponseTypeEnum.FAIL;
    }
    return examResponse;
  }

  Future<ExamResponse> createAccount(User user) async {
    Response response = await _apiService.doPost("/sign-up", {
      "name": user.name,
      "username": user.username,
      "password": user.password
    });
    return _getExamResponse(response);
  }

  Future<ExamResponse> login(User user) async {
    Response response = await _apiService.doPost("/authenticate",
        {"username": user.username, "password": user.password});
    return _getExamResponse(response);
  }

  Future<ExamResponse> getCurrentUser() async {
    Response response = await _apiService.doGet("/user/current");
    return _getExamResponse(response);
  }
}
