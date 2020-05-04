import 'package:examapp/validators/validator.dart';

import 'notEmptyValidator.dart';

class NameValidator implements Validator {
  @override
  String validate(String value) {
    String validate = NotEmptyValidator().validate(value);
    // TODO: build validation
    return validate;
  }
}
