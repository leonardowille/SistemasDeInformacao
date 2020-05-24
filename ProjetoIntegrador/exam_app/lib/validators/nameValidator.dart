import 'package:examapp/validators/validator.dart';

import 'notEmptyValidator.dart';

class NameValidator implements Validator {
  static final RegExp NAME_REGEX = new RegExp(
      r"^([a-zA-Z]{2,}\s[a-zA-z]{1,}'?-?[a-zA-Z]{2,}\s?([a-zA-Z]{1,})?)",
      caseSensitive: false);

  @override
  String validate(String value) {
    String validate = NotEmptyValidator().validate(value);
    if (validate == null && !NAME_REGEX.hasMatch(value)) {
      validate = "Deve ser informado o nome completo.";
    }
    return validate;
  }
}
