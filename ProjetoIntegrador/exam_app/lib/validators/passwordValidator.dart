import 'package:examapp/validators/validator.dart';

import 'notEmptyValidator.dart';

class PasswordValidator implements Validator {
  @override
  String validate(String value) {
    String validate = NotEmptyValidator().validate(value);
    if (validate == null && value.length < 6) {
      validate = "A senha deve conter no mínimo 6 caracteres.";
    }
    return validate;
  }
}
