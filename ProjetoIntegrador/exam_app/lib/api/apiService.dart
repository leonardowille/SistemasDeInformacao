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

  doPost(String path, Map<dynamic, dynamic> body, Function onSuccess, Function onError) async {
    try {
      Response response = await _dio.post(path, data: body);
      onSuccess(response);
    } on DioError catch(e) {
      onError();
      if(e.response != null) {
        print(e.response.data);
        print(e.response.headers);
        print(e.response.request);
      } else{
        print(e.request);
        print(e.message);
      }
    }
  }

  doGet(String path, Function onSuccess, Function onError) async {
    try {
      Response response = await _dio.get(path);
      onSuccess(response);
    } on DioError catch(e) {
      onError();
      if(e.response != null) {
        print(e.response.data);
        print(e.response.headers);
        print(e.response.request);
      } else{
        print(e.request);
        print(e.message);
      }
    }
  }

  doDelete(String path, int id, Function onSuccess, Function onError) async {
    try {
      Response response = await _dio.delete("$path/$id");
      onSuccess(response);
    } on DioError catch(e) {
      onError();
      if(e.response != null) {
        print(e.response.data);
        print(e.response.headers);
        print(e.response.request);
      } else{
        print(e.request);
        print(e.message);
      }
    }
  }
}
