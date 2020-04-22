import 'package:examapp/validators/validator.dart';

class NameValidator extends Validator {
  @override
  String validate(String value) {
    if (value.isEmpty) {
      return 'Please enter some text';
    }
    // TODO: build validation
    return null;
  }
}
