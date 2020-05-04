import 'package:examapp/validators/validator.dart';

import 'notEmptyValidator.dart';

class PasswordValidator implements Validator {
  @override
  String validate(String value) {
    String validate = NotEmptyValidator().validate(value);
    // TODO: build validation
    return validate;
  }
}
