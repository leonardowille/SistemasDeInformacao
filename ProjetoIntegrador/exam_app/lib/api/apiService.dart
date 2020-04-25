import 'package:dio/dio.dart';
import 'package:examapp/services/sharedPreferencesService.dart';

class ApiService {
  Dio _dio;
  SharedPreferencesService sharedPreferencesService =
      SharedPreferencesService();

  ApiService() {
    _createDio();
  }

  _createDio() {
    _dio = Dio(
      BaseOptions(baseUrl: "http://localhost:8081"),
    )..interceptors.add(InterceptorsWrapper(
        onRequest: (RequestOptions options) => _requestInterceptor(options)));
  }

  dynamic _requestInterceptor(RequestOptions options) async {
    String auth = await sharedPreferencesService.getString("jwtAuth");
    if (auth != null) {
      options.headers.addAll({"Authorization": "Bearer $auth"});
    }
    return options;
  }

  doPost(String path, Map<dynamic, dynamic> body) async {
    return await _dio.post(path, data: body);
  }

  doGet(String path) async {
    return await _dio.get(path);
  }
}
