import 'package:shared_preferences/shared_preferences.dart';

class SharedPreferencesService {
  Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  Future<void> setString(String key, String value) async {
    final SharedPreferences prefs = await _prefs;
    prefs.setString(key, value);
  }

  Future<String> getString(String key) async {
    return await _prefs.then((SharedPreferences prefs) {
      return prefs.get(key);
    });
  }

  Future<void> removeKey(String key) async {
    final SharedPreferences prefs = await _prefs;
    prefs.remove(key);
  }
}
